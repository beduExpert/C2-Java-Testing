/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.bedu.testing.demo.monitoreo.entitys.Usuario;
import org.bedu.testing.demo.monitoreo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Fernando
 */
@RequestMapping(value = "/usuario")
@RestController
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@Slf4j
public class UsuarioController {

	private final UsuarioService usuarioService;

	@GetMapping(value = "/id/{idUsuario}", produces = APPLICATION_JSON_VALUE)
	public Usuario obtenerUsuarioPorId(@PathVariable Long idUsuario) {
		return usuarioService.obtenerUsuarioPorId(idUsuario);
	}

	@PostMapping(value = "", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public Usuario agregarNuevoUsuario(@RequestBody Usuario usuario) {
		log.debug("Nuevo usuario [{}]", usuario);
		return usuarioService.agregarNuevoUsuario(usuario);
	}

	@PutMapping(value = "", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public Usuario actualizarUsuario(@RequestBody Usuario usuario) {
		log.debug("Actualizando usuario [{}]", usuario);
		return usuarioService.actualizarUsuario(usuario);
	}

	@DeleteMapping("/{idUsuario}")
	public boolean eliminarUsuario(@PathVariable Long idUsuario) {
		return usuarioService.eliminarUsuario(idUsuario);
	}

	@GetMapping(value = "/{string}", produces = APPLICATION_JSON_VALUE)
	public Page<Usuario> usuarios(@PathVariable String string, @RequestParam("size") int size,
			@RequestParam("page") int page) {

		return usuarioService.obtenerUsuarios(string, page, size);

	}

	@GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
	public Page<Usuario> usuarios(@RequestParam("size") int size, @RequestParam("page") int page) {
		return usuarios("", size, page);
	}

	@GetMapping(value = "/promotor/{string}", produces = APPLICATION_JSON_VALUE)
	public Page<Usuario> promotor(@PathVariable String string, @RequestParam("size") int size,
			@RequestParam("page") int page) {
		return usuarioService.obtenerPromotores(string, page, size);

	}

	@GetMapping(value = "/promotor/", produces = APPLICATION_JSON_VALUE)
	public Page<Usuario> promotor(@RequestParam("size") int size, @RequestParam("page") int page) {
		return promotor("", size, page);
	}
}
