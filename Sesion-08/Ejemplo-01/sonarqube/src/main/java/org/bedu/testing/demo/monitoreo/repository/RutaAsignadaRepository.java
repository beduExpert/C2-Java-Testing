/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.repository;

import java.time.LocalDate;
import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.RutaAsignada;
import org.bedu.testing.demo.monitoreo.entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Fernando
 */
public interface RutaAsignadaRepository extends JpaRepository<RutaAsignada, Long>{    
    RutaAsignada findByUsuarioAndFecha(Usuario usuario, LocalDate fecha);
    List<RutaAsignada> findByUsuarioAndFechaAfter(Usuario usuario, LocalDate fecha);
    RutaAsignada findByIdRuta(Long idRuta);
}
