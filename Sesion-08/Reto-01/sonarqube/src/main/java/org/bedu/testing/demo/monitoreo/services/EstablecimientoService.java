/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services;

import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.DatosEstablecimientoDTO;
import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;
import org.bedu.testing.demo.monitoreo.entitys.EstablecimientoDTO;
import org.bedu.testing.demo.monitoreo.entitys.EstablecimientoMinimalDTO;
import org.bedu.testing.demo.monitoreo.entitys.TipoEstablecimiento;
import org.springframework.data.domain.Page;

/**
 *
 * @author Fernando
 */
public interface EstablecimientoService {

	Establecimiento agregarNuevoEstablecimiento(Establecimiento establecimiento);

	boolean eliminarEstablecimiento(Long idEstablecimiento);

	Establecimiento obtenerEstablecimientoPorId(Long idEstablecimiento);

	Page<EstablecimientoDTO> obtenerEstablecimientoPorNombre(String nombreEstablecimiento, int size, int page);

	Establecimiento obtenerEstablecimientoPorNombre(String nombreEstablecimiento);

	Establecimiento actualizarEstablecimiento(Establecimiento establecimiento);

	Page<EstablecimientoDTO> obtenerTodosLosEstablecimientos(int page, int size);

	Page<EstablecimientoDTO> obtenerEstablecimientosActivos(String nombreEstablecimiento, int page, int size);

	Page<EstablecimientoDTO> obtenerEstablecimientosNuevos(String nombreEstablecimiento, int page, int size);

	Page<EstablecimientoDTO> obtenerEstablecimientosNoEncontrados(String nombreEstablecimiento, int page, int size);

	Page<EstablecimientoDTO> obtenerEstablecimientosInactivos(String nombreEstablecimiento, int page, int size);

	List<Establecimiento> obtenerEstablecimientosSinZonaAsignada();

	List<Establecimiento> obtenerTodosLosEstablecimientos();

	void cambiarEstatus(Long idEstablecimiento, String estatus);

	List<EstablecimientoDTO> establecimientosDeZona(Long idZona);

	Long contarEstablecimientosDeZona(Long idZona);

	void actualizarServicioEstablecimiento(Long idEstablecimiento, String correo, String telefono,
			TipoEstablecimiento tipoEstablecimiento);

	DatosEstablecimientoDTO obtenerDatosEstablecimiento(Long idEstablecimiento);

	boolean cambiarFotografia(Long idEstablecimiento, String fotografiaBase64);

	String obtenerFotografia(Long idEstablecimiento);

	Iterable<EstablecimientoMinimalDTO> establecimientosZonaAndZipCode(Long idZona, String zipCode);

	Iterable<String> zipCodesByZone(Long zoneId);

}
