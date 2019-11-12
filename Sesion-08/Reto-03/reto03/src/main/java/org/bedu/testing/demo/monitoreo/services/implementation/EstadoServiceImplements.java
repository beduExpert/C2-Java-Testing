/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services.implementation;

import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Estado;
import org.bedu.testing.demo.monitoreo.repository.EstadoRepository;
import org.bedu.testing.demo.monitoreo.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

/**
 *
 * @author Fernando
 */
@Service
@Secured("IS_AUTHENTICATED_FULLY")
public class EstadoServiceImplements implements EstadoService{
    @Autowired
    private EstadoRepository estadoRepository;
    
    @Override
    public List<Estado> obtenerTodosLosEstados() {
        return estadoRepository.findAll();
    }

    @Override
    public Estado obtenerEstadoPorId(Long idEstado) {
        return estadoRepository.findByIdEstado(idEstado);
    }
    
}
