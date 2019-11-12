/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services;

import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;
import org.bedu.testing.demo.monitoreo.entitys.ReporteEstablecimiento;
import org.springframework.data.domain.Page;

/**
 *
 * @author cypunk
 */
public interface ReporteEstablecimientoService {

	Page<Establecimiento> obtenerEstablecimeintosReportadosPorNombre(String nombre, int page, int size);

	Page<Establecimiento> obtenerEstablecimientosReportadosNuevosPorNombre(String nombre, int page, int size);

	void aprobarReporte(ReporteEstablecimiento reporteEstablecimiento, Long idUsuario);

	void rechazaReporte(ReporteEstablecimiento reporteEstablecimiento, Long idUsuario);

	void reportarNuevo(Establecimiento establecimiento);

	ReporteEstablecimiento ultimoReporte(Establecimiento establecimiento);
}
