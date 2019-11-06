/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.repository;

import org.bedu.testing.demo.monitoreo.entitys.Zona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Fernando
 */
public interface ZonaRepository extends JpaRepository<Zona, Long>{
    //@formatter:off
    @Query("select distinct z from Zona z "        
        + "where z.descripcion like :string ")
    //@formatter:on
    Page<Zona> searchByString(@Param("string") String string, Pageable pageRequest);
    
    Zona findByIdZona(Long idZona);
}
