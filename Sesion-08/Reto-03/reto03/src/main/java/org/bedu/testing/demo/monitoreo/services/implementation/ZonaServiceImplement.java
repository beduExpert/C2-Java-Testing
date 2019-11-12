/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services.implementation;

import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;
import org.bedu.testing.demo.monitoreo.entitys.Zona;
import org.bedu.testing.demo.monitoreo.repository.EstablecimientoRepository;
import org.bedu.testing.demo.monitoreo.repository.ZonaRepository;
import org.bedu.testing.demo.monitoreo.services.ZonaService;
import org.bedu.testing.demo.monitoreo.tools.LoggedUserAuthorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
public class ZonaServiceImplement implements ZonaService {

	private static final String MATCH_TEMPLATE = "%%%s%%";
	private static final Page<Zona> EMPTY_PAGE = Page.empty();
	private final LoggedUserAuthorities authority;
	private final ZonaRepository zonaRepository;
	private final EstablecimientoRepository establecimientoRepository;

	@Override
	public Page<Zona> obtenerTodasLasZonas(String string, int page, int size) {

		String matcher = String.format(MATCH_TEMPLATE, string);

		log.debug("Matcher: [{}]", matcher);

		PageRequest pageRequest = PageRequest.of(page, size);

		if (authority.isRocketAdmin() || authority.isMarchandAdmin()) {
			return zonaRepository.searchByString(matcher, pageRequest);
		}

		log.error("No es un adminsitrador");
		return EMPTY_PAGE;

	}

	@Override
	public boolean eliminarZona(Long idZona) {
		try {
			zonaRepository.delete(zonaRepository.findByIdZona(idZona));
			return true;
		} catch (Exception e) {
			log.error("Error al eliminar la zona: " + e);
			return false;
		}
	}

	@Override
	public Zona obtenerZonaPorId(Long idZona) {
		return zonaRepository.findByIdZona(idZona);
	}

	@Override
	public boolean quitarEstablecimientoDeZona(Long idZona, Long idEstablecimiento) {
		Establecimiento establecimiento = establecimientoRepository.findByIdEstablecimiento(idEstablecimiento);
		establecimiento.setZona(null);
		establecimientoRepository.save(establecimiento);
		return true;
	}

	@Override
	public boolean asignarEstablecimientoAZona(Long idZona, Long idEstablecimiento) {

		Zona zona = zonaRepository.findByIdZona(idZona);
		Establecimiento establecimiento = establecimientoRepository.findByIdEstablecimiento(idEstablecimiento);
		establecimiento.setZona(zona);

		establecimientoRepository.save(establecimiento);

		return true;

	}

	@Override
	public Zona crearZona(String nombreZona) {
		Zona zona = new Zona();
		zona.setDescripcion(nombreZona);
		return zonaRepository.save(zona);
	}

	@Override
	public Iterable<Zona> zonas() {
		return zonaRepository.findAll();
	}

}
