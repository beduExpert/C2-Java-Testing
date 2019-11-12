/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services;

import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Colonia;

/**
 *
 * @author Fernando
 */
public interface ColoniaService {
	Iterable<Colonia> obtenerTodasLasColonias();

	List<Colonia> obtenerColoniasPorDelegacion(Long idMunicipio, Long idEstado);
}
