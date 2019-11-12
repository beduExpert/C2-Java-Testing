/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;
import org.bedu.testing.demo.monitoreo.entitys.Perfil;
import org.bedu.testing.demo.monitoreo.entitys.RutaAsignada;
import org.bedu.testing.demo.monitoreo.entitys.Usuario;
import org.bedu.testing.demo.monitoreo.entitys.Visita;
import org.bedu.testing.demo.monitoreo.excepciones.MonitoreoException;
import org.bedu.testing.demo.monitoreo.repository.RutaAsignadaRepository;
import org.bedu.testing.demo.monitoreo.services.EstablecimientoService;
import org.bedu.testing.demo.monitoreo.services.RutaAsignadaService;
import org.bedu.testing.demo.monitoreo.services.UsuarioService;
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
public class RutaAsignadaServiceImplement implements RutaAsignadaService {
	private static final ArrayList<Establecimiento> EMPTY_LIST = new ArrayList<>();
	private final RutaAsignadaRepository rutaRepository;
	private final UsuarioService usuarioService;
	private final EstablecimientoService establecimientoService;

	@Override
	public RutaAsignada agregarNuevaRutaAsignada(String sfecha, Long idUsuario, Long idUsuarioLoggeado) {

		validatePromotor(idUsuario);

		Usuario usuario = usuarioService.obtenerUsuarioPorId(idUsuario);
		LocalDate fechaRuta = LocalDate.parse(sfecha);

		RutaAsignada rutaExistente = rutaRepository.findByUsuarioAndFecha(usuario, fechaRuta);

		if (rutaExistente != null) {
			String msg = String.format("El usuario %s ya tiene una ruta para la fecha %s", usuario.getUsername(),
					sfecha);
			log.error(msg);
			throw new MonitoreoException(msg);
		}

		RutaAsignada rutaAsignada = new RutaAsignada();

		rutaAsignada.setFecha(fechaRuta);
		rutaAsignada.setFechaElaboracion(LocalDate.now());

		rutaAsignada.setUsuario(usuarioService.obtenerUsuarioPorId(idUsuario));

		return rutaRepository.save(rutaAsignada);
	}

	private void validatePromotor(Long idUsuario) {
		Perfil p = usuarioService.obtenerUsuarioPorId(idUsuario).getPerfil();
		if (p.getIdPerfil() != Perfil.PROMOTOR_ID) {
			String message = String.format("Wrong profile [%s]", p.getDescripcion());
			log.error(message);
			throw new MonitoreoException(message);
		}
	}

	@Override
	public RutaAsignada buscarRutaPorIdRuta(Long idRuta) {

		return rutaRepository.findByIdRuta(idRuta);
	}

	@Override
	public List<RutaAsignada> buscarRutasProximasPorUsuario(Long idUsuario) {
		validatePromotor(idUsuario);
		Usuario usuario = usuarioService.obtenerUsuarioPorId(idUsuario);
		// Se resta 1 para que busque rutas incluyendo el día de Hoy
		return rutaRepository.findByUsuarioAndFechaAfter(usuario, LocalDate.now().minusDays(1));
	}

	@Override
	public RutaAsignada eliminarEstablecimientoDeRuta(Long idRuta, Long idEstablecimiento) {

		RutaAsignada ruta = rutaRepository.findByIdRuta(idRuta);
		List<Establecimiento> listaestab = ruta.getEstablecimiento();
		List<Establecimiento> nuevaLista = new ArrayList<>();

		Establecimiento estabAEliminar = establecimientoService.obtenerEstablecimientoPorId(idEstablecimiento);

		for (Establecimiento e : listaestab) {
			if (e.equals(estabAEliminar)) {
				//
			} else {
				nuevaLista.add(e);
			}
		}

		ruta.setEstablecimiento(nuevaLista);

		return rutaRepository.save(ruta);
	}

	@Override
	public RutaAsignada agregarEstablecimientoARuta(Long idRuta, Long idEstablecimiento) {
		RutaAsignada ruta = rutaRepository.findByIdRuta(idRuta);
		List<Establecimiento> listaEstablecimientos = ruta.getEstablecimiento();
		Establecimiento nuevoEstab = establecimientoService.obtenerEstablecimientoPorId(idEstablecimiento);

		listaEstablecimientos.add(nuevoEstab);

		ruta.setEstablecimiento(listaEstablecimientos);

		return rutaRepository.save(ruta);
	}

	@Override
	public List<Establecimiento> obtenerEstablecimientosQueNoSonParteDeUnaRuta(Long idRuta) {
		List<Establecimiento> resultado = new ArrayList<>();

		List<Establecimiento> todosEstablecimientos = establecimientoService.obtenerTodosLosEstablecimientos();

		List<Establecimiento> establecimientosEnRuta = rutaRepository.findByIdRuta(idRuta).getEstablecimiento();

		for (Establecimiento e : todosEstablecimientos) {
			if (establecimientosEnRuta.contains(e)) {
				//
			} else {
				resultado.add(e);
			}
		}
		return resultado;
	}

	@Override
	public RutaAsignada buscarRutasPorUsuarioYFecha(Usuario usuario, LocalDate fecha) {
		validatePromotor(usuario.getIdUsuario());
		return getRuta(usuario, fecha);

	}

	private RutaAsignada getRuta(Usuario usuario, LocalDate fecha) {
		RutaAsignada ruta = rutaRepository.findByUsuarioAndFecha(usuario, fecha);
		if (ruta == null) {
			log.debug("Ruta no existente previamente. Creando");
			ruta = new RutaAsignada();
			ruta.setFecha(fecha);
			ruta.setUsuario(usuarioService.getLoggedUser());
			ruta.setEstablecimiento(EMPTY_LIST);
		}

		log.debug("Ruta: [{}]", ruta);

		return ruta;
	}

	@Override
	public RutaAsignada buscarRutaDelDia() {

		RutaAsignada ruta = buscarRutasPorUsuarioYFecha(usuarioService.getLoggedUser(), LocalDate.now());

		ruta.setEstablecimiento(filtrarRuta(ruta));

		return ruta;
	}

	private List<Establecimiento> filtrarRuta(RutaAsignada ruta) {
		log.debug("Filtrando establecimientos");
		List<Establecimiento> establecimeintosFiltrado = new ArrayList<>();

		for (Establecimiento e : ruta.getEstablecimiento()) {
			List<Visita> visitas = e.getVisitas();

			Collections.sort(visitas);
			Collections.reverse(visitas);

			LocalDate ultimaVisita = visitas.isEmpty() ? null : visitas.get(0).getFecha().toLocalDate();

			log.debug("Última visita del establecimiento [{}] : [{}]", e.getIdEstablecimiento(), ultimaVisita);

			if (ultimaVisita == null || ultimaVisita.isBefore(LocalDate.now())) {
				log.debug("El establecimiento sigue pendiente");
				establecimeintosFiltrado.add(e);
			}

		}

		log.debug("La ruta tiene [{}] establecimeintos", establecimeintosFiltrado.size());

		return establecimeintosFiltrado;
	}

}
