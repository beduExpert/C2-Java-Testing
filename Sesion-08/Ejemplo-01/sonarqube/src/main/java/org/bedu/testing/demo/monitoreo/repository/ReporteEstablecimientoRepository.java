/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.repository;

import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;
import org.bedu.testing.demo.monitoreo.entitys.ReporteEstablecimiento;
import org.bedu.testing.demo.monitoreo.enums.TipoReporte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author cypunk
 */
public interface ReporteEstablecimientoRepository extends PagingAndSortingRepository<ReporteEstablecimiento, Long> {

	//@formatter:off
	@Query("select r.establecimiento " +
			"from ReporteEstablecimiento r " + 
			"where r.tipoReporte = :tipoReporte " + 
			"and r.fechaRevision is null " + 
			"and upper(r.establecimiento.nombreEstablecimiento) like upper(:name)")
	//@formatter:on
	Page<Establecimiento> findEstablecimientosWithActiveReportByStatusAndName(@Param("name") String name,
			@Param("tipoReporte") TipoReporte tipoReporte, Pageable pageRequest);

	//@formatter:off
	@Query("select r.establecimiento " + 
	       "from ReporteEstablecimiento r " +
	       "where r.fechaRevision is null " + 
	       "and (r.establecimiento.nombreEstablecimiento) like upper(:name)")
	//@formatter:on
	Page<Establecimiento> findEstablecimientosWithAciveReportByName(@Param("name") String name, Pageable pageRequest);

	List<ReporteEstablecimiento> findAllByEstablecimiento(Establecimiento establecimiento);
}
