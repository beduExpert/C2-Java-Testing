/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services;

import java.time.LocalDate;
import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;
import org.bedu.testing.demo.monitoreo.entitys.RutaAsignada;
import org.bedu.testing.demo.monitoreo.entitys.Usuario;

/**
 *
 * @author Fernando
 */
public interface RutaAsignadaService {
	RutaAsignada agregarNuevaRutaAsignada(String fecha, Long idUsuario, Long idUsuarioLoggeado);

	RutaAsignada eliminarEstablecimientoDeRuta(Long idRuta, Long idEstablecimiento);

	RutaAsignada agregarEstablecimientoARuta(Long idRuta, Long idEstablecimiento);

	List<RutaAsignada> buscarRutasProximasPorUsuario(Long idUsuario);

	RutaAsignada buscarRutaPorIdRuta(Long idRuta);

	List<Establecimiento> obtenerEstablecimientosQueNoSonParteDeUnaRuta(Long idRuta);

	RutaAsignada buscarRutasPorUsuarioYFecha(Usuario usuario, LocalDate fecha);

	RutaAsignada buscarRutaDelDia();
}
