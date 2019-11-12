/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.controllers;

import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.TipoEstablecimiento;
import org.bedu.testing.demo.monitoreo.services.TipoEstablecimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Fernando
 */
@RequestMapping(value = "/tipoEstablecimiento", produces = APPLICATION_JSON_VALUE)
@RestController
public class TipoEstablecimientoController {
	@Autowired
	private TipoEstablecimientoService tipoEstablecimientoService;

	@GetMapping("")
	public List<TipoEstablecimiento> obtenerTodosLosTiposDeEstablecimiento() {
		return tipoEstablecimientoService.obtenertodosLosTiposEstablecimiento();
	}
}
