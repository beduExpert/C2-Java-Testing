/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.repository;

import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;
import org.bedu.testing.demo.monitoreo.entitys.Mensaje;
import org.bedu.testing.demo.monitoreo.entitys.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author cypunk
 */
public interface MensajeRepository extends PagingAndSortingRepository<Mensaje, Long> {
	List<Mensaje> findByEstablecimiento(Establecimiento establecimiento);

	List<Mensaje> findByEstablecimientoAndLeido(Establecimiento establecimiento, boolean leido);

	List<Mensaje> findByUsuario(Usuario usuario);

	Page<Mensaje> findByLeido(boolean leido, PageRequest pageRequest);

	Mensaje findByIdMensaje(Long idMensaje);

	@Query("select distinct m.establecimiento from Mensaje m where lower(m.establecimiento.nombreEstablecimiento) like lower(:nombre) and m.leido = false")
	Page<Establecimiento> findEstablecimientosWithMessagesAndByName(@Param("nombre") String nombre,
			Pageable pageRequest);

}
