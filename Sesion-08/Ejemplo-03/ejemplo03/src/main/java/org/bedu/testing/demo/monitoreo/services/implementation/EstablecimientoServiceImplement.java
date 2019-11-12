/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services.implementation;

import java.util.List;

import lombok.SneakyThrows;
import org.bedu.testing.demo.monitoreo.entitys.DatosEstablecimientoDTO;
import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;
import org.bedu.testing.demo.monitoreo.entitys.EstablecimientoDTO;
import org.bedu.testing.demo.monitoreo.entitys.EstablecimientoMinimalDTO;
import org.bedu.testing.demo.monitoreo.entitys.EstablecimientoVisitaView;
import org.bedu.testing.demo.monitoreo.entitys.Fotografia;
import org.bedu.testing.demo.monitoreo.entitys.ReporteEstablecimiento;
import org.bedu.testing.demo.monitoreo.entitys.TipoEstablecimiento;
import org.bedu.testing.demo.monitoreo.enums.Estatus;
import org.bedu.testing.demo.monitoreo.excepciones.MonitoreoException;
import org.bedu.testing.demo.monitoreo.repository.EstablecimientoRepository;
import org.bedu.testing.demo.monitoreo.services.EstablecimientoService;
import org.bedu.testing.demo.monitoreo.services.EstablecimientoVisitaSevice;
import org.bedu.testing.demo.monitoreo.services.ReporteEstablecimientoService;
import org.bedu.testing.demo.monitoreo.services.UsuarioService;
import org.bedu.testing.demo.monitoreo.tools.EstablecimientoEstablecimientoDTOConverter;
import org.bedu.testing.demo.monitoreo.tools.google.GoogleGeolocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * *
 *
 * @author Fernando
 */
@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@Slf4j
@Secured("IS_AUTHENTICATED_FULLY")
public class EstablecimientoServiceImplement implements EstablecimientoService {

	private static final String MATCH_TEMPLATE = "%%%s%%";

	private final EstablecimientoRepository establecimientoRepository;
	private final GoogleGeolocator geolocator;
	private final EstablecimientoEstablecimientoDTOConverter converter;
	private final UsuarioService usuarioService;
	private final ReporteEstablecimientoService reporteEstablecimientoService;
	private final EstablecimientoVisitaSevice establecimientoVisitaService;

	@Override
	@SneakyThrows
	public Establecimiento agregarNuevoEstablecimiento(Establecimiento establecimiento) {

		establecimiento = geolocator.geolocate(establecimiento);

		establecimiento.setEstatus(Estatus.PENDIENTE);
		reporteEstablecimientoService.reportarNuevo(establecimiento);

		return establecimientoRepository.save(establecimiento);
	}

	@Override
	public boolean eliminarEstablecimiento(Long idEstablecimiento) {
		try {
			establecimientoRepository.delete(establecimientoRepository.findByIdEstablecimiento(idEstablecimiento));
			return true;
		} catch (Exception er) {
			log.error(String.format("Error al eliminad establecimiento [%s]", idEstablecimiento), er);
			return false;
		}
	}

	@Override
	public Establecimiento obtenerEstablecimientoPorId(Long idEstablecimiento) {

		return establecimientoRepository.findByIdEstablecimiento(idEstablecimiento);

	}

	@Override
	public Page<EstablecimientoDTO> obtenerEstablecimientoPorNombre(String nombreEstablecimiento, int size, int page) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return transform(nombreEstablecimiento, pageRequest);
	}

	private Page<EstablecimientoDTO> transform(String nombreEstablecimiento, PageRequest pageRequest) {
		Page<Establecimiento> establecimientosPage = establecimientoRepository
				.findAllByNombreEstablecimientoLikeIgnoreCase(String.format(MATCH_TEMPLATE, nombreEstablecimiento),
						pageRequest);

		return converter.convert(establecimientosPage);
	}

	@Override
	public Establecimiento obtenerEstablecimientoPorNombre(String nombreEstablecimiento) {
		return establecimientoRepository.findByNombreEstablecimiento(nombreEstablecimiento);
	}

	@Override
    @SneakyThrows
	public Establecimiento actualizarEstablecimiento(Establecimiento establecimiento) {

		return establecimientoRepository.save(geolocator.geolocate(establecimiento));
	}

	@Override
	public Page<EstablecimientoDTO> obtenerTodosLosEstablecimientos(int page, int size) {

		PageRequest pageRequest = PageRequest.of(page, size);

		return transform("", pageRequest);

	}

	private Page<EstablecimientoDTO> obtenerEstablecimientosPorEstatus(String nombreEstablecimiento, Estatus estatus,
			int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);

		Page<Establecimiento> establecimientosPage = establecimientoRepository
				.findByNombreEstablecimientoLikeIgnoreCaseAndEstatus(
						String.format(MATCH_TEMPLATE, nombreEstablecimiento), estatus, pageRequest);

		return converter.convert(establecimientosPage);
	}

	@Override
	public List<Establecimiento> obtenerEstablecimientosSinZonaAsignada() {
		List<Establecimiento> sinZona = establecimientoRepository.findByZona(null);

		log.debug("Se encontraron [{}] establecimientos sin zona. [{}]", sinZona.size(), sinZona);

		return sinZona;
	}

	@Override
	public List<Establecimiento> obtenerTodosLosEstablecimientos() {
		return establecimientoRepository.findAll();
	}

	@Override
	public Page<EstablecimientoDTO> obtenerEstablecimientosActivos(String nombreEstablecimiento, int page, int size) {
		return obtenerEstablecimientosPorEstatus(nombreEstablecimiento, Estatus.ACTIVO, page, size);
	}

	@Override
	public Page<EstablecimientoDTO> obtenerEstablecimientosNuevos(String nombreEstablecimiento, int page, int size) {
		return converter.convert(reporteEstablecimientoService.obtenerEstablecimientosReportadosNuevosPorNombre(
				String.format(MATCH_TEMPLATE, nombreEstablecimiento), page, size));
	}

	@Override
	public Page<EstablecimientoDTO> obtenerEstablecimientosInactivos(String nombreEstablecimiento, int page, int size) {
		return obtenerEstablecimientosPorEstatus(nombreEstablecimiento, Estatus.INACTIVO, page, size);
	}

	@Override
	public void cambiarEstatus(Long idEstablecimiento, String estatus) {

		cambiarEstatus(idEstablecimiento, Estatus.valueOf(estatus));
	}

	private void cambiarEstatus(Long idEstablecimiento, Estatus estatus) {
		Establecimiento e = obtenerEstablecimientoPorId(idEstablecimiento);
		e.setEstatus(estatus);

		revisarReporte(estatus, e);

		actualizarEstablecimiento(e);
	}

	private void revisarReporte(Estatus estatus, Establecimiento e) {
		ReporteEstablecimiento re = reporteEstablecimientoService.ultimoReporte(e);
		if (re != null) {
			actualizarReporte(re, estatus);
		}
	}

	private void actualizarReporte(ReporteEstablecimiento reporteEstablecimiento, Estatus estatus) {

		if (estatus.equals(Estatus.ACTIVO)) {
			reporteEstablecimientoService.aprobarReporte(reporteEstablecimiento,
					usuarioService.getLoggedUser().getIdUsuario());
		} else if (estatus.equals(Estatus.INACTIVO)) {
			reporteEstablecimientoService.rechazaReporte(reporteEstablecimiento,
					usuarioService.getLoggedUser().getIdUsuario());
		} else {
			String message = String
					.format("El establecimiento debe pasar a ACTIVO o INACTIVO o PENDIENTE. Se recibió [%s]", estatus);
			log.error(message);
			throw new MonitoreoException(message);
		}
	}

	@Override
	public Page<EstablecimientoDTO> obtenerEstablecimientosNoEncontrados(String nombreEstablecimiento, int page,
			int size) {

		Page<EstablecimientoVisitaView> noEncontrados = establecimientoVisitaService
				.establecimientosNoEncontradoPorNombre(nombreEstablecimiento, PageRequest.of(page, size));

		return converter.convertView(noEncontrados);
	}

	@Override
	public List<EstablecimientoDTO> establecimientosDeZona(Long idZona) {

		return converter.convert(establecimientoRepository.findByZonaIdZona(idZona));
	}

	@Override
	public Long contarEstablecimientosDeZona(Long idZona) {

		return establecimientoRepository.countByZonaIdZona(idZona);
	}

	@Override
	public void actualizarServicioEstablecimiento(Long idEstablecimiento, String correo, String telefono,
			TipoEstablecimiento tipoEstablecimiento) {

		Establecimiento e = establecimientoRepository.findByIdEstablecimiento(idEstablecimiento);

		if (e == null) {
			String message = String.format("Establecimiento [%s] no encontrado", idEstablecimiento);
			log.error(message);
			throw new MonitoreoException(message);
		}

		e.setCorreo(correo);
		e.setTelefono(telefono);
		e.setTipoEstablecimiento(tipoEstablecimiento);

		establecimientoRepository.save(e);

	}

	@Override
	public DatosEstablecimientoDTO obtenerDatosEstablecimiento(Long idEstablecimiento) {
		return establecimientoRepository.obtenerDatosEstablecimiento(idEstablecimiento);
	}

	@Override
	public boolean cambiarFotografia(Long idEstablecimiento, String fotografiaBase64) {

		validarFotoNoVacia(idEstablecimiento, fotografiaBase64);

		log.debug("Asignando fotografía al establecimiento [{}]", idEstablecimiento);
		log.debug("Se reciben {} caracteres en la cadena base64", fotografiaBase64.length());

		Establecimiento establecimiento = establecimientoRepository.findByIdEstablecimiento(idEstablecimiento);

		stablishmentExists(idEstablecimiento, establecimiento);

		Fotografia fotografia = establecimiento.getFotografia() == null ? new Fotografia()
				: establecimiento.getFotografia();

		log.debug("La fotografía existía? [{}]", (fotografia.getId() != null));

		fotografia.setBinario(fotografiaBase64);
		establecimiento.setFotografia(fotografia);
		establecimientoRepository.save(establecimiento);

		return true;
	}

	private void validarFotoNoVacia(Long idEstablecimiento, String fotografiaBase64) {
		if (fotografiaBase64 == null) {
			String message = String.format(
					"No se recibieron datos. No se actualizará la fotografía del establecimiento [%s]",
					idEstablecimiento);
			log.error(message);
			throw new MonitoreoException(message);
		}
	}

	private void stablishmentExists(Long idEstablecimiento, Establecimiento establecimiento) {
		if (establecimiento == null) {
			String message = String.format("Establecimiento [%s] inexistente", idEstablecimiento);
			log.error(message);
			throw new MonitoreoException(message);
		}
	}

	@Override
	public String obtenerFotografia(Long idEstablecimiento) {
		log.debug("Recuperando fotografía del establecimiento [{}]", idEstablecimiento);
		Establecimiento establecimiento = establecimientoRepository.findByIdEstablecimiento(idEstablecimiento);

		stablishmentExists(idEstablecimiento, establecimiento);
		Fotografia fotografia = establecimiento.getFotografia();

		return fotografia == null ? null : fotografia.getBinario();
	}

	@Override
	public Iterable<EstablecimientoMinimalDTO> establecimientosZonaAndZipCode(Long idZona, String zipCode) {

		return establecimientoRepository.findAllByZoneMinimal(idZona, zipCode);

	}

	@Override
	public Iterable<String> zipCodesByZone(Long zoneId) {
		return establecimientoRepository.findAllZipCodesByZone(zoneId);
	}

}
