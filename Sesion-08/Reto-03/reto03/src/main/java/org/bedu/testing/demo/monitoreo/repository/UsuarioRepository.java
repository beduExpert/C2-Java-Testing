/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.repository;

import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Fernando
 */
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {
	Usuario findByIdUsuario(Long idUsuario);

	Usuario findOneByUsername(String username);

	//@formatter:off
	@Query("select distinct u from Usuario u "
			+ "where (u.nombre like :string "
			+ "or u.apellidoPaterno like :string "
			+ "or u.apellidoMaterno like :string "
			+ "or u.username like :string) "
			+ "and u.estatus = true")
	//@formatter:on
	Page<Usuario> searchByString(@Param("string") String string, Pageable pageRequest);

	//@formatter:off
	@Query("select distinct u from Usuario u "
			+ "where (u.nombre like :string "
			+ "or u.apellidoPaterno like :string "
			+ "or u.apellidoMaterno like :string "
			+ "or u.username like :string)"
			+ "and u.perfil.idPerfil in :profiles")
	//@formatter:on
	Page<Usuario> searchByStringAndProfileID(@Param("string") String string, @Param("profiles") List<Long> profiles,
			Pageable pageRequest);

	@Query("select u.password from Usuario u where u.idUsuario = :idUsuario")
	String getPasswordByUserId(@Param("idUsuario") Long idUsuario);

}
