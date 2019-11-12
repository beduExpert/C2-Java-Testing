/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.repository;

import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.DatosEstablecimientoDTO;
import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;
import org.bedu.testing.demo.monitoreo.entitys.EstablecimientoMinimalDTO;
import org.bedu.testing.demo.monitoreo.entitys.Zona;
import org.bedu.testing.demo.monitoreo.enums.Estatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Fernando
 */
public interface EstablecimientoRepository extends PagingAndSortingRepository<Establecimiento, Long> {

	Establecimiento findByIdEstablecimiento(Long idEstablecimiento);

	Page<Establecimiento> findAllByNombreEstablecimientoLikeIgnoreCase(String nombreEstablecimiento,
			Pageable pageRequest);

	Establecimiento findByNombreEstablecimiento(String nombreEstablecimiento);

	Page<Establecimiento> findByNombreEstablecimientoLikeIgnoreCaseAndEstatus(String nombreEstablecimiento,
			Estatus estatus, Pageable pageRequest);

	@Override
	List<Establecimiento> findAll();

	List<Establecimiento> findByZona(Zona zona);

	List<Establecimiento> findByZonaIdZona(Long idZona);

	Long countByZonaIdZona(Long idZona);

	// @formatter: off
	@Query("select new com.mozcalti.rocketdelivery.marchand.monitoreo.entitys.DatosEstablecimientoDTO(e.telefono, e.correo, e.tipoEstablecimiento)"
			+ " from Establecimiento e" + " where e.idEstablecimiento = :idEstablecimiento")
	// @formatter: on
	DatosEstablecimientoDTO obtenerDatosEstablecimiento(@Param("idEstablecimiento") Long idEstablecimiento);

	// @formatter: off
	@Query("select new com.mozcalti.rocketdelivery.marchand.monitoreo.entitys.EstablecimientoMinimalDTO(e.idEstablecimiento, e.calleYNumero, e.colonia.descripcion)"
			+ " from Establecimiento e" 
			+ " where e.zona.idZona = :idZona"
			+ " and e.colonia.compositeKey.codigoPostal = :zipCode")
	// @formatter: on
	Iterable<EstablecimientoMinimalDTO> findAllByZoneMinimal(@Param("idZona") Long idZona, @Param("zipCode") String zipCode);

	// @formatter: off
	@Query("select distinct e.colonia.compositeKey.codigoPostal"
			+ " from Establecimiento e" 
			+ " where e.zona.idZona = :idZona")
	// @formatter: on
	Iterable<String> findAllZipCodesByZone(@Param("idZona") Long idZona);
}
