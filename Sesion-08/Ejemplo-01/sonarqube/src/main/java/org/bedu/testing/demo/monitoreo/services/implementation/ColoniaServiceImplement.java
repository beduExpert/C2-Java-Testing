/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services.implementation;

import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Colonia;
import org.bedu.testing.demo.monitoreo.entitys.Municipio;
import org.bedu.testing.demo.monitoreo.repository.ColoniaRepository;
import org.bedu.testing.demo.monitoreo.services.ColoniaService;
import org.bedu.testing.demo.monitoreo.services.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 *
 * @author Fernando
 */
@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@Secured("IS_AUTHENTICATED_FULLY")
public class ColoniaServiceImplement implements ColoniaService {

	private final ColoniaRepository coloniaRepository;
	private final MunicipioService municipioService;

	@Override
	public Iterable<Colonia> obtenerTodasLasColonias() {
		return coloniaRepository.findAll();
	}

	@Override
	public List<Colonia> obtenerColoniasPorDelegacion(Long idMunicipio, Long idEstado) {
		Municipio delegacion = municipioService.obtenerDelegacionPorId(idMunicipio, idEstado);
		return coloniaRepository.findByMunicipio(delegacion);
	}

}
