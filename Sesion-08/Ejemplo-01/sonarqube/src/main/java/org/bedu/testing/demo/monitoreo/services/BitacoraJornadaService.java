/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services;

import org.bedu.testing.demo.monitoreo.entitys.BitacoraJornada;

/**
 *
 * @author Fernando
 */
public interface BitacoraJornadaService {

	BitacoraJornada agregarNuevaBitacoraJornada();

	BitacoraJornada actualizarBitacoraJornada(long kilometrosRecorridos);

	boolean eliminarBitacoraJornada(Long idBitacoraJornada);

	BitacoraJornada obtenerBitacoraJornadaPorId(Long idBitacoraJornada);

}
