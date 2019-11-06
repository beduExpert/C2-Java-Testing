/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.BitacoraJornada;
import org.bedu.testing.demo.monitoreo.entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Fernando
 */
public interface BitacoraJornadaRepository extends JpaRepository<BitacoraJornada, Long> {
	BitacoraJornada findByIdBitacoraJornada(Long idBitacoraJornada);

	List<BitacoraJornada> findByUsuario(Usuario usuario);

	BitacoraJornada findOneByUsuarioAndInicioJornada(Usuario usuario, LocalDateTime timestamp);

	BitacoraJornada findOneByUsuarioAndInicioJornadaBetween(Usuario usuario, LocalDateTime inicio, LocalDateTime fin);
}
