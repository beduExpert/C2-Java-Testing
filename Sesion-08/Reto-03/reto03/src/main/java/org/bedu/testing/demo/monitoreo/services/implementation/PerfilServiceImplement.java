/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services.implementation;

import static org.bedu.testing.demo.monitoreo.constants.MonitoreoProfiles.isMarchandProfile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Perfil;
import org.bedu.testing.demo.monitoreo.repository.PerfilRepository;
import org.bedu.testing.demo.monitoreo.services.PerfilService;
import org.bedu.testing.demo.monitoreo.tools.LoggedUserAuthorities;
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
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@Slf4j
@Secured("IS_AUTHENTICATED_FULLY")
public class PerfilServiceImplement implements PerfilService {
	private static final Iterable<Perfil> EMPTY_LIST = Arrays.asList();

	private final PerfilRepository perfilRepository;
	private final LoggedUserAuthorities matchers;

	private List<Perfil> reducedList;

	@Override
	public Iterable<Perfil> obtenerTodosLosPerfiles() {
		log.debug("loading profiles");

		Iterable<Perfil> fullList = perfilRepository.findAll();

		if (matchers.isRocketAdmin()) {
			log.debug("RD Admin");
			return fullList;
		}

		if (matchers.isMarchandAdmin()) {
			log.debug("Marchand Admin");
			return reduceList(fullList);
		}

		log.error("No es un administrador");
		return EMPTY_LIST;
	}

	private Iterable<Perfil> reduceList(Iterable<Perfil> fullList) {

		if (reducedList == null) {
			loadReducedList(fullList);
		}

		return reducedList;

	}

	private void loadReducedList(Iterable<Perfil> fullList) {

		reducedList = new ArrayList<>();

		for (Perfil p : fullList) {
			if (isMarchandProfile((p.getIdPerfil()))) {
				reducedList.add(p);
			}
		}
	}

	@Override
	public Perfil obtenerPerfilPorId(Long idPerfil) {
		return perfilRepository.findByIdPerfil(idPerfil);
	}

}
