/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.bedu.testing.demo.monitoreo.entitys.ReporteEstablecimiento;
import org.bedu.testing.demo.monitoreo.services.ReporteEstablecimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 *
 * @author cypunk
 */
@RequestMapping(value = "/reportes", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@RestController
public class ReporteEstablecimientoController {

	private final ReporteEstablecimientoService reporteEstablecimientoService;

	@PostMapping(value = "/aprobar", consumes = APPLICATION_JSON_VALUE)
	public void aprobarReporte(@RequestBody ReporteEstablecimiento reporteEstablecimiento,
			@RequestParam("idUsuario") Long idUsuario) {
		reporteEstablecimientoService.aprobarReporte(reporteEstablecimiento, idUsuario);
	}

	@PostMapping(value = "/rechazar", consumes = APPLICATION_JSON_VALUE)
	public void rechazarReporte(@RequestBody ReporteEstablecimiento reporteEstablecimiento,
			@RequestParam("idUsuario") Long idUsuario) {
		reporteEstablecimientoService.rechazaReporte(reporteEstablecimiento, idUsuario);
	}
}
