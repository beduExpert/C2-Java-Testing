/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services.implementation;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.bedu.testing.demo.monitoreo.entitys.BitacoraJornada;
import org.bedu.testing.demo.monitoreo.entitys.Usuario;
import org.bedu.testing.demo.monitoreo.repository.BitacoraJornadaRepository;
import org.bedu.testing.demo.monitoreo.services.BitacoraJornadaService;
import org.bedu.testing.demo.monitoreo.services.RutaAsignadaService;
import org.bedu.testing.demo.monitoreo.services.UsuarioService;
import org.bedu.testing.demo.monitoreo.services.VisitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Fernando
 */
@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@Slf4j
@Secured("IS_AUTHENTICATED_FULLY")
public class BitacoraJornadaServiceImplement implements BitacoraJornadaService {

	private final BitacoraJornadaRepository bitacoraJornadaRepository;
	private final UsuarioService usuarioService;

	private final RutaAsignadaService rutaAsignadaService;
	private final VisitaService visitaService;

	@Override
	public synchronized BitacoraJornada agregarNuevaBitacoraJornada() {
		Usuario loggedUser = usuarioService.getLoggedUser();

		LocalDateTime inicio = LocalDate.now().atStartOfDay();
		LocalDateTime fin = LocalDate.now().plusDays(1).atStartOfDay();

		log.debug("Buscando bitácora para el usuario {} entre {} y {}", loggedUser.getUsername(), inicio, fin);

		BitacoraJornada bitacoraJornada = bitacoraJornadaRepository.findOneByUsuarioAndInicioJornadaBetween(loggedUser,
				inicio, fin);

		if (bitacoraJornada == null) {
			log.debug("No existía Jornada. Creando nueva");
			bitacoraJornada = createAndSaveBitacora(loggedUser);
		} else {
			log.debug("Jornada existente {}", bitacoraJornada);
		}

		return bitacoraJornada;
	}

	private BitacoraJornada createAndSaveBitacora(Usuario usuario) {
		BitacoraJornada bitacoraJornada = new BitacoraJornada();
		bitacoraJornada.setInicioJornada(LocalDateTime.now());
		bitacoraJornada.setKmsRecorridos(0);
		bitacoraJornada.setUsuario(usuario);

		return bitacoraJornadaRepository.save(bitacoraJornada);
	}

	@Override
	public BitacoraJornada actualizarBitacoraJornada(long kilometrosRecorridos) {

		Usuario usuario = usuarioService.getLoggedUser();
		LocalDateTime now = LocalDateTime.now();

		int numeroVisitas = visitaService.obtenerPorPromotorYFecha(usuario, now.toLocalDate()).size();
		int numeroAsigando = rutaAsignadaService.buscarRutasPorUsuarioYFecha(usuario, now.toLocalDate())
				.getEstablecimiento().size();

		if (numeroVisitas == numeroAsigando) {
			return finalizarJornada(kilometrosRecorridos, usuario, now);
		}

		return null;

	}

	private BitacoraJornada finalizarJornada(long kilometrosRecorridos, Usuario usuario, LocalDateTime now) {
		BitacoraJornada bitacoraJornadaActual = bitacoraJornadaRepository.findOneByUsuarioAndInicioJornada(usuario,
				now);
		bitacoraJornadaActual.setFinJornada(LocalDateTime.now());
		bitacoraJornadaActual.setKmsRecorridos(kilometrosRecorridos);
		return bitacoraJornadaRepository.save(bitacoraJornadaActual);
	}

	@Override
	public boolean eliminarBitacoraJornada(Long idBitacoraJornada) {
		try {
			bitacoraJornadaRepository.delete(bitacoraJornadaRepository.findByIdBitacoraJornada(idBitacoraJornada));
			return true;
		} catch (Exception err) {
			log.error("Error aliminar bitacoraJornada. " + err);
			return false;
		}
	}

	@Override
	public BitacoraJornada obtenerBitacoraJornadaPorId(Long idBitacoraJornada) {
		return bitacoraJornadaRepository.findByIdBitacoraJornada(idBitacoraJornada);
	}

}
