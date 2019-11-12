/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;
import org.bedu.testing.demo.monitoreo.entitys.RutaAsignada;
import org.bedu.testing.demo.monitoreo.services.RutaAsignadaService;
import org.bedu.testing.demo.monitoreo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/ruta")
@RestController
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class RutaAsignadaController {
	private final RutaAsignadaService rutaAsignadaService;
	private final UsuarioService usuarioService;

	@GetMapping(value = "/{idRuta}", produces = APPLICATION_JSON_VALUE)
	public RutaAsignada obtenerRutaPorIdRuta(@PathVariable Long idRuta) {
		return rutaAsignadaService.buscarRutaPorIdRuta(idRuta);
	}

	@PostMapping(value = "/", produces = APPLICATION_JSON_VALUE)
	public RutaAsignada agregarNuevaRuta(@RequestParam("fecha") String sfecha,
			@RequestParam("idUsuario") Long idUsuario) {
		return rutaAsignadaService.agregarNuevaRutaAsignada(sfecha, idUsuario,
				usuarioService.getLoggedUser().getIdUsuario());
	}

	@GetMapping(value = "/establecimientos/{idRuta}", produces = APPLICATION_JSON_VALUE)
	public List<Establecimiento> obtenerEstablecimientosQueNoSonParteDeUnaRuta(@PathVariable Long idRuta) {
		return rutaAsignadaService.obtenerEstablecimientosQueNoSonParteDeUnaRuta(idRuta);
	}

	@GetMapping(value = "/idUsuario/{idUsuario}", produces = APPLICATION_JSON_VALUE)
	public List<RutaAsignada> obtenerRutaPorUsuarioYFecha(@PathVariable Long idUsuario) {
		return rutaAsignadaService.buscarRutasProximasPorUsuario(idUsuario);
	}

	@PutMapping(value = "quitarEstablecimiento", produces = APPLICATION_JSON_VALUE)
	public RutaAsignada eliminarEstablecimientoDeRuta(@RequestParam("idRuta") Long idRuta,
			@RequestParam("idEstablecimiento") Long idEstablecimiento) {
		return rutaAsignadaService.eliminarEstablecimientoDeRuta(idRuta, idEstablecimiento);
	}

	@PutMapping(value = "agregarEstablecimiento", produces = APPLICATION_JSON_VALUE)
	public RutaAsignada agregarEstablecimientoARuta(@RequestParam("idRuta") Long idRuta,
			@RequestParam("idEstablecimiento") Long idEstablecimiento) {
		return rutaAsignadaService.agregarEstablecimientoARuta(idRuta, idEstablecimiento);
	}

	@GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
	public RutaAsignada obtenerRutaDelDia() {

		return rutaAsignadaService.buscarRutaDelDia();

	}
}
