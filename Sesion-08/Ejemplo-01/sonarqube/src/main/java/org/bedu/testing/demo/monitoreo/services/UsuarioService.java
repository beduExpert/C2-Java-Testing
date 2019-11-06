/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services;

import org.bedu.testing.demo.monitoreo.entitys.Usuario;
import org.springframework.data.domain.Page;

/**
 *
 * @author Fernando
 */
public interface UsuarioService {
	Usuario obtenerUsuarioPorId(Long idUsuario);

	Usuario getUserByUsername(String username);

	Usuario agregarNuevoUsuario(Usuario usuario);

	boolean eliminarUsuario(Long idUsuario);

	Usuario actualizarUsuario(Usuario usuario);

	Page<Usuario> obtenerUsuarios(String string, int page, int size);

	Page<Usuario> obtenerPromotores(String string, int page, int size);

	Usuario getLoggedUser();
}
