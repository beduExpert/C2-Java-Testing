/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Colonia;
import org.bedu.testing.demo.monitoreo.services.ColoniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Fernando
 */
@RequestMapping(value = "/colonia", produces = APPLICATION_JSON_VALUE)
@RestController
public class ColoniaController {
	@Autowired
	private ColoniaService coloniaService;

	@GetMapping("")
	public Iterable<Colonia> obtenerTodasLasColonias() {
		return coloniaService.obtenerTodasLasColonias();
	}

	@GetMapping("/{idEstado}/{idMunicipio}")
	public List<Colonia> obtenerTodasLasColonias(@PathVariable Long idMunicipio, @PathVariable Long idEstado) {
		return coloniaService.obtenerColoniasPorDelegacion(idMunicipio, idEstado);
	}
}
