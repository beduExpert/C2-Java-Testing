/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.BitacoraJornada;
import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;
import org.bedu.testing.demo.monitoreo.entitys.RutaAsignada;
import org.bedu.testing.demo.monitoreo.services.BitacoraJornadaService;
import org.bedu.testing.demo.monitoreo.services.RutaAsignadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Fernando
 */
@RequestMapping(value = "/bitacora", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class BitacoraJornadaController {

	@Data
	private static class JornadaRutaWrapper {
		private BitacoraJornada jornada;
		private List<Establecimiento> establecimientos;
		private int intervalo;

		public JornadaRutaWrapper(BitacoraJornada jornada, RutaAsignada ruta, int intervalo) {
			this.jornada = jornada;
			this.establecimientos = ruta.getEstablecimiento();
			this.intervalo = intervalo;
		}

	}

	@Data
	private static class BitacoraInputDTO {
		private long kilometrosRecorridos;
	}

	private final BitacoraJornadaService bitacoraJornadaService;
	private final RutaAsignadaService rutaService;

	@Setter(onMethod_ = { @Value("${rd.marchand.monitoreo.intervalo}") })
	private int intervalo;

	@GetMapping("/{idBitacora}")
	public BitacoraJornada obtenerBitacoraPorId(@PathVariable Long idBitacora) {
		return bitacoraJornadaService.obtenerBitacoraJornadaPorId(idBitacora);
	}

	@PostMapping("/")
	public JornadaRutaWrapper agregarNuevaBitacora() {
		return new JornadaRutaWrapper(bitacoraJornadaService.agregarNuevaBitacoraJornada(),
				rutaService.buscarRutaDelDia(), intervalo);
	}

	@PostMapping("/finalizarJornada")
	public boolean finalizarJornada(@RequestBody BitacoraInputDTO inputDTO) {
		bitacoraJornadaService.actualizarBitacoraJornada(inputDTO.getKilometrosRecorridos());
		return true;
	}

	@DeleteMapping("/{idBitacora}")
	public boolean eliminarNuevaBitacora(@PathVariable Long idBitacora) {
		return bitacoraJornadaService.eliminarBitacoraJornada(idBitacora);
	}
}
