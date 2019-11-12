/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.bedu.testing.demo.monitoreo.entitys.EstablecimientoMinimalDTO;
import org.bedu.testing.demo.monitoreo.entitys.Zona;
import org.bedu.testing.demo.monitoreo.services.EstablecimientoService;
import org.bedu.testing.demo.monitoreo.services.ZonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 *
 * @author Fernando
 */
@RequestMapping(value = "/zona", produces = APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class ZonaController {
	private final ZonaService zonaService;
	private final EstablecimientoService establecimientoService;

	@GetMapping(value = "/{string}", produces = APPLICATION_JSON_VALUE)
	public Page<Zona> zonas(@PathVariable String string, @RequestParam("size") int size,
			@RequestParam("page") int page) {
		return zonaService.obtenerTodasLasZonas(string, page, size);
	}

	@GetMapping(value = "/{idZona}/establecimientos/{zipCode}", produces = APPLICATION_JSON_VALUE)
	public Iterable<EstablecimientoMinimalDTO> establecimientosZona(@PathVariable Long idZona,
			@PathVariable String zipCode) {
		return establecimientoService.establecimientosZonaAndZipCode(idZona, zipCode);
	}

	@GetMapping(value = "/id/{idZona}", produces = APPLICATION_JSON_VALUE)
	public Zona zonas(@PathVariable Long idZona) {
		return zonaService.obtenerZonaPorId(idZona);
	}

	@GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
	public Page<Zona> zonas(@RequestParam("size") int size, @RequestParam("page") int page) {
		return zonas("", size, page);
	}

	@DeleteMapping(value = "/{idZona}")
	public boolean eliminarZona(@PathVariable Long idZona) {
		return zonaService.eliminarZona(idZona);
	}

	@DeleteMapping(value = "/{idZona}/{idEstablecimiento}")
	public boolean quitarEstablecimientoDeZona(@PathVariable Long idZona, @PathVariable Long idEstablecimiento) {
		return zonaService.quitarEstablecimientoDeZona(idZona, idEstablecimiento);
	}

	@PutMapping(value = "/{idZona}/{idEstablecimiento}")
	public boolean asignarEstablecimientoAZona(@PathVariable Long idZona, @PathVariable Long idEstablecimiento) {
		return zonaService.asignarEstablecimientoAZona(idZona, idEstablecimiento);
	}

	@PostMapping(value = "/{nombreZona}", produces = APPLICATION_JSON_VALUE)
	public Zona crearZona(@PathVariable String nombreZona) {
		return zonaService.crearZona(nombreZona);
	}
}
