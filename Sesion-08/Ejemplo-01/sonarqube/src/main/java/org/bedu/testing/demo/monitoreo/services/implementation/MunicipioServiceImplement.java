/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services.implementation;

import java.util.Optional;

import org.bedu.testing.demo.monitoreo.entitys.Estado;
import org.bedu.testing.demo.monitoreo.entitys.Municipio;
import org.bedu.testing.demo.monitoreo.entitys.keys.MunicipioCompositeKey;
import org.bedu.testing.demo.monitoreo.excepciones.MonitoreoException;
import org.bedu.testing.demo.monitoreo.repository.EstadoRepository;
import org.bedu.testing.demo.monitoreo.repository.MunicipioRepository;
import org.bedu.testing.demo.monitoreo.services.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Fernando
 */
@Service
@Slf4j
@Secured("IS_AUTHENTICATED_FULLY")
public class MunicipioServiceImplement implements MunicipioService {
	@Autowired
	private MunicipioRepository delegacionRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Override
	public Iterable<Municipio> obtenerTodasLasDelegaciones() {
		return delegacionRepository.findAll();
	}

	@Override
	public Iterable<Municipio> obtenerDelegacionesPorEstado(Long idEstado) {
		Estado estado = estadoRepository.findByIdEstado(idEstado);
		return delegacionRepository.findByEstado(estado);
	}

	@Override
	public Municipio obtenerDelegacionPorId(Long idMunicipio, Long idEstado) {
		MunicipioCompositeKey key = new MunicipioCompositeKey(idMunicipio, idEstado);
		Optional<Municipio> response = delegacionRepository.findById(key);
		if (response.isPresent()) {
			return response.get();
		} else {
			String msg = String.format("No se pudo encontrar el municipio [%d] del estado [%d]", idMunicipio, idEstado);
			log.error(msg);
			throw new MonitoreoException(msg);
		}
	}

}
