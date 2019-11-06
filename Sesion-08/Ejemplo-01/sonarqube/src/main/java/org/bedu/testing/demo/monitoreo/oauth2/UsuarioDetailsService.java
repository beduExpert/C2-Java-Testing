/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.oauth2;

import org.bedu.testing.demo.monitoreo.entitys.Usuario;
import org.bedu.testing.demo.monitoreo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 *
 * @author Fernando
 */
@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class UsuarioDetailsService implements UserDetailsService {

	private final UsuarioService service;

	@Override
	public UserDetails loadUserByUsername(String nombreUsuario) {
		Usuario usuario = service.getUserByUsername(nombreUsuario);
		return new UsuarioDetails(usuario);
	}

}
