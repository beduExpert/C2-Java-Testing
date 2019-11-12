/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services;

import org.bedu.testing.demo.monitoreo.entitys.Municipio;

/**
 *
 * @author Fernando
 */
public interface MunicipioService {
	Iterable<Municipio> obtenerTodasLasDelegaciones();

	Iterable<Municipio> obtenerDelegacionesPorEstado(Long idEstado);

	Municipio obtenerDelegacionPorId(Long idMunicipio, Long idEstado);
}
