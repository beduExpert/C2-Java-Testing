/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services;

import org.bedu.testing.demo.monitoreo.entitys.Perfil;

/**
 *
 * @author Fernando
 */
public interface PerfilService {
	Iterable<Perfil> obtenerTodosLosPerfiles();
	Perfil obtenerPerfilPorId(Long idPerfil);
}
