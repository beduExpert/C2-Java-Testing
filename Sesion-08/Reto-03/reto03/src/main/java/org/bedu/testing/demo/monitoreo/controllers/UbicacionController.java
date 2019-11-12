package org.bedu.testing.demo.monitoreo.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.math.BigDecimal;

import org.bedu.testing.demo.monitoreo.entitys.Ubicacion;
import org.bedu.testing.demo.monitoreo.excepciones.MonitoreoException;
import org.bedu.testing.demo.monitoreo.services.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ubicacion")
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@Slf4j
public class UbicacionController {

	@Data
	private static class Geolocation {
		private BigDecimal latitud;
		private BigDecimal longitud;
	}

	private final UbicacionService service;

	@PostMapping(value = "", consumes = APPLICATION_JSON_VALUE)
	public boolean saveLocation(@RequestBody Geolocation geolocation) {
		log.debug("Geolocation: [{}]", geolocation);

		if (geolocation.latitud == null || geolocation.longitud == null) {
			String message = String.format("Par de coordenadas inválido %s", geolocation.toString());
			log.error(message);
			throw new MonitoreoException(message);
		}

		service.saveLocation(geolocation.getLatitud(), geolocation.getLongitud());

		return true;
	}

	@GetMapping(value = "/{idPromotor}", produces = APPLICATION_JSON_VALUE)
	public Ubicacion getLasKnownLocation(@PathVariable Long idPromotor) {
		return service.getLocation(idPromotor);
	}

}
