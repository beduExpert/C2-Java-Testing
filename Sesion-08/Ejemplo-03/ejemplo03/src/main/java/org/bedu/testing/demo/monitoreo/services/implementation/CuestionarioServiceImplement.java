/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services.implementation;

import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Cuestionario;
import org.bedu.testing.demo.monitoreo.repository.CuestionarioRepository;
import org.bedu.testing.demo.monitoreo.services.CuestionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Fernando
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@Secured("IS_AUTHENTICATED_FULLY")
public class CuestionarioServiceImplement implements CuestionarioService {
	private final CuestionarioRepository cuestionarioRepository;

	@Override
	public Cuestionario agregarNuevoCuestionario(Cuestionario cuestionario) {

		log.debug("Encuesta recibida [{}]", cuestionario);

		return cuestionarioRepository.save(cuestionario);
	}

	@Override
	public List<Cuestionario> obtenerTodosCuestionarios() {
		return cuestionarioRepository.findAll();
	}

	@Override
	public Cuestionario obtenerCuestionarioPorId(Long idCuestionario) {
		return cuestionarioRepository.findByIdCuestionario(idCuestionario);
	}

}
