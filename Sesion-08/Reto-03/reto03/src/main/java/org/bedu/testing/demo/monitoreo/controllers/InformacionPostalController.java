package org.bedu.testing.demo.monitoreo.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.bedu.testing.demo.monitoreo.entitys.InformacionPostal;
import org.bedu.testing.demo.monitoreo.services.CodigoPostalService;
import org.bedu.testing.demo.monitoreo.services.EstablecimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cp")
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class InformacionPostalController {
	private final CodigoPostalService service;
	private final EstablecimientoService establecimientoService;

	@GetMapping(value = "/{codigoPostal}", produces = APPLICATION_JSON_VALUE)
	public InformacionPostal getInformacionPostal(@PathVariable String codigoPostal) {
		return service.informacionPostal(codigoPostal);
	}

	@GetMapping(value = "/zona/{zoneId}", produces = APPLICATION_JSON_VALUE)
	public Iterable<String> zoneCodes(@PathVariable Long zoneId) {
		return establecimientoService.zipCodesByZone(zoneId);
	}
}
