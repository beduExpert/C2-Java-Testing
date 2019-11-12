/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services;

import org.bedu.testing.demo.monitoreo.entitys.Zona;
import org.springframework.data.domain.Page;

/**
 *
 * @author Fernando
 */
public interface ZonaService {
	Page<Zona> obtenerTodasLasZonas(String string, int page, int size);

	Zona obtenerZonaPorId(Long idZona);

	boolean eliminarZona(Long idZona);

	boolean quitarEstablecimientoDeZona(Long idZona, Long idEstablecimiento);

	boolean asignarEstablecimientoAZona(Long idZona, Long idEstablecimiento);

	Zona crearZona(String nombreZona);

	Iterable<Zona> zonas();

}
