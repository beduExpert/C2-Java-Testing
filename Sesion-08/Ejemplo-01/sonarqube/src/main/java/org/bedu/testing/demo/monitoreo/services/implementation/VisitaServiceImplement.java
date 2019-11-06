/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services.implementation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;
import org.bedu.testing.demo.monitoreo.entitys.RutaAsignada;
import org.bedu.testing.demo.monitoreo.entitys.Usuario;
import org.bedu.testing.demo.monitoreo.entitys.Visita;
import org.bedu.testing.demo.monitoreo.enums.ResultadoVisita;
import org.bedu.testing.demo.monitoreo.repository.VisitaRepository;
import org.bedu.testing.demo.monitoreo.services.EstablecimientoService;
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
public class VisitaServiceImplement implements VisitaService {

	private final VisitaRepository visitaRepository;
	private final EstablecimientoService establecimientoService;
	private final UsuarioService usuarioService;
	private final RutaAsignadaService rutasService;

	@Override
	public Visita agregarVisita(Long idEstablecimiento, String comentario, ResultadoVisita resultado) {

		Establecimiento establecimiento = establecimientoService.obtenerEstablecimientoPorId(idEstablecimiento);
		Visita visita = crearVisita(comentario, resultado);
		establecimiento.getVisitas().add(visita);
		establecimientoService.actualizarEstablecimiento(establecimiento);

		log.debug("Visita agregada");

		return visita;
	}

	private Visita crearVisita(String comentario, ResultadoVisita resultado) {
		Visita visita = new Visita();
		visita.setFecha(LocalDateTime.now());
		visita.setUsuario(usuarioService.getLoggedUser());
		visita.setComentario(comentario);
		visita.setResultado(resultado);

		log.debug("Datos de visita [{}]", visita);

		return visita;
	}

	@Override
	public List<Visita> obtenerPorPromotorYFecha(Usuario usuario, LocalDate fecha) {
		return visitaRepository.findByUsuarioAndFechaBetween(usuario, fecha.atStartOfDay(), fecha.atTime(23, 59));
	}

	@Override
	public RutaAsignada visitasDeUsuarioDelDia(long idPromotor) {

		return rutasService.buscarRutasPorUsuarioYFecha(usuarioService.obtenerUsuarioPorId(idPromotor),
				LocalDate.now());
	}

}
