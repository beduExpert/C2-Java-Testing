/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services;

import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;
import org.bedu.testing.demo.monitoreo.entitys.EstablecimientoDTO;
import org.bedu.testing.demo.monitoreo.entitys.Mensaje;
import org.bedu.testing.demo.monitoreo.entitys.Usuario;
import org.springframework.data.domain.Page;

/**
 *
 * @author cypunk
 */
public interface MensajeService {

	Mensaje cambiarEstatusMensaje(Long idMensaje);

	List<Mensaje> obtenerMensajesPorEstablecimiento(Establecimiento establecimiento);

	List<Mensaje> obtenerMensajesPorEstablecimientoYEstatus(Establecimiento establecimiento, boolean estatus);

	List<Mensaje> obtenerMensajesPorUsuario(Usuario usuario);

	Page<Mensaje> obtenertodosLosMensajes(int page, int size);

	Page<Mensaje> obtenertodosLosMensajesPorEstatus(boolean estatus, int page, int size);


	Page<EstablecimientoDTO> obtenerEstablecimientosConMensajesActivosPorNombre(String nombre, int page, int size);
}
