/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.repository;

import org.bedu.testing.demo.monitoreo.entitys.AccionesSistema;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author cypunk
 */
public interface AccionesSistemaRepository extends JpaRepository<AccionesSistema, Long>{
    
}
