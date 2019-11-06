/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services.implementation;

import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.TipoEstablecimiento;
import org.bedu.testing.demo.monitoreo.repository.TipoEstablecimientoRepository;
import org.bedu.testing.demo.monitoreo.services.TipoEstablecimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

/**
 *
 * @author Fernando
 */
@Service
@Secured("IS_AUTHENTICATED_FULLY")
public class TipoEstablecimientoServiceImplement implements TipoEstablecimientoService {
	@Autowired
	private TipoEstablecimientoRepository tipoEstablecimientoRepository;

	@Override
	public List<TipoEstablecimiento> obtenertodosLosTiposEstablecimiento() {
		return tipoEstablecimientoRepository.findAll();
	}

}
