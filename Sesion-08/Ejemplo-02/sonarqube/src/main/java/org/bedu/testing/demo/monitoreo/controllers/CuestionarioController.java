/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.controllers;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Cuestionario;
import org.bedu.testing.demo.monitoreo.services.CuestionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Fernando
 */
@RequestMapping(value = "/cuestionario", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RestController
public class CuestionarioController {
    private final CuestionarioService cuestionarioService;
    
    @PostMapping("/")
    public Cuestionario agregarNuevoCuestionario(@RequestBody Cuestionario cuestionario){
        return cuestionarioService.agregarNuevoCuestionario(cuestionario);
    }
    
    @GetMapping("/todos")
    public List<Cuestionario> obtenerTodosCuestionarios(){
        return cuestionarioService.obtenerTodosCuestionarios();
    }
    
    @GetMapping("/{idCuestionario}")
    public Cuestionario obtenerCuestionarioPorId(@PathVariable Long idCuestionario){
        return cuestionarioService.obtenerCuestionarioPorId(idCuestionario);
    }
}
