/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services.implementation;

import org.bedu.testing.demo.monitoreo.constants.MonitoreoProfiles;
import org.bedu.testing.demo.monitoreo.entitys.Usuario;
import org.bedu.testing.demo.monitoreo.repository.UsuarioRepository;
import org.bedu.testing.demo.monitoreo.services.UsuarioService;
import org.bedu.testing.demo.monitoreo.tools.LoggedUserAuthorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class UsuarioServiceImplement implements UsuarioService {
	private static final String MATCH_TEMPLATE = "%%%s%%";

	private static final Page<Usuario> EMPTY_PAGE = Page.empty();

	private final UsuarioRepository usuarioRepository;
	private final LoggedUserAuthorities authority;
	private final PasswordEncoder passwordEncoder;

	@Override
	public Usuario obtenerUsuarioPorId(Long idUsuario) {
		return usuarioRepository.findByIdUsuario(idUsuario);
	}

	@Override
	public Usuario getUserByUsername(String username) {
		return usuarioRepository.findOneByUsername(username);
	}

	@Override
	public Usuario agregarNuevoUsuario(Usuario usuario) {

		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuario.setEstatus(true);

		return usuarioRepository.save(usuario);
	}

	@Override
	public boolean eliminarUsuario(Long idUsuario) {
		try {

			Usuario usuario = usuarioRepository.findByIdUsuario(idUsuario);
			usuario.setEstatus(false);
			usuarioRepository.save(usuario);
			return true;
		} catch (Exception er) {
			log.error("error eliminar usuario. " + er);
			return false;
		}

	}

	@Override
	public Usuario actualizarUsuario(Usuario usuario) {

		log.debug("Actualizando a [{}]", usuario);

		if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
			log.debug("Actualizando usuario [{}] con password", usuario.getIdUsuario());
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		} else {
			log.debug("Actualizando usuario [{}] sin password", usuario.getIdUsuario());
			usuario.setPassword(usuarioRepository.getPasswordByUserId(usuario.getIdUsuario()));
		}

		return usuarioRepository.save(usuario);
	}

	@Override
	public Page<Usuario> obtenerUsuarios(String string, int page, int size) {
		String matcher = String.format(MATCH_TEMPLATE, string);

		log.debug("Matcher: [{}]", matcher);

		PageRequest pageRequest = PageRequest.of(page, size);

		if (authority.isRocketAdmin()) {
			return usuarioRepository.searchByString(matcher, pageRequest);
		} else if (authority.isMarchandAdmin()) {
			return usuarioRepository.searchByStringAndProfileID(matcher, MonitoreoProfiles.MONITOREO_PROFILES,
					pageRequest);
		}

		log.error("No es un adminsitrador");
		return EMPTY_PAGE;
	}

	@Override
	public Usuario getLoggedUser() {
		return usuarioRepository.findOneByUsername(authority.getLoggedUsername());
	}

	@Override
	public Page<Usuario> obtenerPromotores(String string, int page, int size) {

		String matcher = String.format(MATCH_TEMPLATE, string);

		log.debug("Matcher: [{}]", matcher);

		PageRequest pageRequest = PageRequest.of(page, size);

		if (authority.isRocketAdmin()) {
			return usuarioRepository.searchByStringAndProfileID(matcher, MonitoreoProfiles.PROMOTORES, pageRequest);
		}

		log.error("No es un adminsitrador");
		return EMPTY_PAGE;

	}

}
