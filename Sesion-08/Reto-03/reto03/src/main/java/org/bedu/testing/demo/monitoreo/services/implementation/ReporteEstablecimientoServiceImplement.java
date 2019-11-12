/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services.implementation;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;
import org.bedu.testing.demo.monitoreo.entitys.ReporteEstablecimiento;
import org.bedu.testing.demo.monitoreo.enums.TipoReporte;
import org.bedu.testing.demo.monitoreo.repository.ReporteEstablecimientoRepository;
import org.bedu.testing.demo.monitoreo.services.ReporteEstablecimientoService;
import org.bedu.testing.demo.monitoreo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author cypunk
 */
@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@Slf4j
@Secured("IS_AUTHENTICATED_FULLY")
public class ReporteEstablecimientoServiceImplement implements ReporteEstablecimientoService {

	private final ReporteEstablecimientoRepository reporteRepository;
	private final UsuarioService usuarioService;

	@Override
	public Page<Establecimiento> obtenerEstablecimeintosReportadosPorNombre(String nombre, int page, int size) {
		return reporteRepository.findEstablecimientosWithAciveReportByName(nombre, PageRequest.of(page, size));
	}

	@Override
	public Page<Establecimiento> obtenerEstablecimientosReportadosNuevosPorNombre(String nombre, int page, int size) {
		return reporteRepository.findEstablecimientosWithActiveReportByStatusAndName(nombre, TipoReporte.NUEVO,
				PageRequest.of(page, size));
	}

	@Override
	public void aprobarReporte(ReporteEstablecimiento reporteEstablecimiento, Long idUsuario) {

		reporteEstablecimiento.setFechaRevision(LocalDateTime.now());
		reporteRepository.save(reporteEstablecimiento);

	}

	@Override
	public void rechazaReporte(ReporteEstablecimiento reporteEstablecimiento, Long idUsuario) {
		aprobarReporte(reporteEstablecimiento, idUsuario);
	}

	@Override
	public void reportarNuevo(Establecimiento establecimiento) {

		ReporteEstablecimiento reporteEstablecimiento = new ReporteEstablecimiento();
		reporteEstablecimiento.setFecha(LocalDateTime.now());
		reporteEstablecimiento.setEstablecimiento(establecimiento);
		reporteEstablecimiento.setUsuario(usuarioService.getLoggedUser());
		reporteEstablecimiento.setTipoReporte(TipoReporte.NUEVO);

		reporteRepository.save(reporteEstablecimiento);
	}

	@Override
	public ReporteEstablecimiento ultimoReporte(Establecimiento establecimiento) {
		log.debug("Recuperando lista de reportes");
		List<ReporteEstablecimiento> reportes = reporteRepository.findAllByEstablecimiento(establecimiento);

		log.debug("Se encontraron [{}] reportes", reportes.size());

		Collections.sort(reportes);

		Collections.reverse(reportes);

		return !reportes.isEmpty() ? reportes.get(0) : null;
	}

}
