/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.bedu.testing.demo.monitoreo.entitys.Municipio;
import org.bedu.testing.demo.monitoreo.services.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Fernando
 */
@RequestMapping(value = "/delegacion", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@RestController
public class MunicipioController {
	@Autowired
	private MunicipioService delegacionService;

	@GetMapping("/")
	public Iterable<Municipio> obtenerTodasLasDelegaciones() {
		return delegacionService.obtenerTodasLasDelegaciones();
	}

	@GetMapping("/{idEstado}")
	public Iterable<Municipio> obtenerDelegacionesPorEstado(@PathVariable Long idEstado) {
		return delegacionService.obtenerDelegacionesPorEstado(idEstado);
	}
}
