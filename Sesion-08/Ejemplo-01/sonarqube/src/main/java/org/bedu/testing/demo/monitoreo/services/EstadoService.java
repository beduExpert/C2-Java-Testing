/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services;

import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Estado;

/**
 *
 * @author Fernando
 */
public interface EstadoService {
    List<Estado> obtenerTodosLosEstados();
    Estado obtenerEstadoPorId(Long idEstado);
}
