/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.repository;

import java.time.LocalDate;
import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.KmVigencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Fernando
 */
public interface KmVigenciaRepository extends JpaRepository<KmVigencia, Long> {
	List<KmVigencia> findByFechaVigenciaLessThanEqual(LocalDate fechaLimite);

	@Query("select k from KmVigencia k where k.idKmVigencia = (select max(k2.idKmVigencia) from KmVigencia k2 where k2.fechaVigencia <= :date)")
	KmVigencia findActiveFare(@Param("date") LocalDate date);
}
