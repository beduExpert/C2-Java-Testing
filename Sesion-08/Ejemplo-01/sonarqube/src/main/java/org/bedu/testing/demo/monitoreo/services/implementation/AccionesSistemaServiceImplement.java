/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services.implementation;

import org.bedu.testing.demo.monitoreo.entitys.AccionesSistema;
import org.bedu.testing.demo.monitoreo.repository.AccionesSistemaRepository;
import org.bedu.testing.demo.monitoreo.services.AccionesSistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

/**
 *
 * @author cypunk
 */
@Service
@Secured("IS_AUTHENTICATED_FULLY")
public class AccionesSistemaServiceImplement implements AccionesSistemaService {
	@Autowired
	private AccionesSistemaRepository accionesSistemaRepository;

	@Override
	public void agregarRegistro(AccionesSistema accionesSistema) {
		accionesSistemaRepository.save(accionesSistema);
	}

}
