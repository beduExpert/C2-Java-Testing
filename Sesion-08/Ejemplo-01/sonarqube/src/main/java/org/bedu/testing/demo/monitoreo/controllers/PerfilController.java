/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.bedu.testing.demo.monitoreo.entitys.Perfil;
import org.bedu.testing.demo.monitoreo.services.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Fernando
 */
@RequestMapping(value = "/perfil")
@RestController
public class PerfilController {
	@Autowired
	private PerfilService perfilService;

	@GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
	public Iterable<Perfil> obtenerTodosLosPerfiles() {
		return perfilService.obtenerTodosLosPerfiles();
	}
}
