/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services;

import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Cuestionario;

/**
 *
 * @author Fernando
 */
public interface CuestionarioService {
    Cuestionario agregarNuevoCuestionario(Cuestionario cuestionario);
    List<Cuestionario> obtenerTodosCuestionarios();
    Cuestionario obtenerCuestionarioPorId(Long idCuestionario);
}
