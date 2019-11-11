/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;
import org.bedu.testing.demo.monitoreo.entitys.EstablecimientoDTO;
import org.bedu.testing.demo.monitoreo.services.EstablecimientoService;
import org.bedu.testing.demo.monitoreo.services.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Fernando
 */
@RequestMapping(value = "/establecimiento", produces = APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@Slf4j
public class EstablecimientoController {

	private static final String EMPTY_STRING = "";
	private final EstablecimientoService establecimientoService;
	private final MensajeService mensajeService;

	@Data
	@NoArgsConstructor
	private static class ActualizarEstatosInputDTO {

		Long id;
		String estatus;
	}

	@Data
	@RequiredArgsConstructor
	private static class CountWrapper {

		private final Long establecimientos;
	}

	@PostMapping(value = "", consumes = APPLICATION_JSON_VALUE)
	public Establecimiento agregarNuevoEstablecimiento(@RequestBody Establecimiento establecimiento) {

		log.debug("Guardando establecimeinto [{}]", establecimiento);

		return establecimientoService.agregarNuevoEstablecimiento(establecimiento);
	}

	@PutMapping(value = "", consumes = APPLICATION_JSON_VALUE)
	public Establecimiento actualizarEstablecimiento(@RequestBody Establecimiento establecimiento) {
		return establecimientoService.actualizarEstablecimiento(establecimiento);
	}

	@DeleteMapping("/{idEstablecimiento}")
	public boolean eliminarEstablecimiento(@PathVariable Long idEstablecimiento) {
		return establecimientoService.eliminarEstablecimiento(idEstablecimiento);
	}

	@GetMapping("/id/{idEstablecimiento}")
	public Establecimiento obtenerEstablecimientoPorId(@PathVariable Long idEstablecimiento) {
		return establecimientoService.obtenerEstablecimientoPorId(idEstablecimiento);
	}

	@GetMapping("/{nombreEstablecimiento}")
	public Page<EstablecimientoDTO> obtenerEstablecimientoPorNombre(@PathVariable String nombreEstablecimiento,
			@RequestParam int size, @RequestParam int page) {
		return establecimientoService.obtenerEstablecimientoPorNombre(nombreEstablecimiento, size, page);
	}

	@GetMapping("/mensajes/{nombreEstablecimiento}")
	public Page<EstablecimientoDTO> obtenerEstablecimientoPorNombreConMensajes(
			@PathVariable String nombreEstablecimiento, @RequestParam int size, @RequestParam int page) {
		return mensajeService.obtenerEstablecimientosConMensajesActivosPorNombre(nombreEstablecimiento, page, size);
	}

	@GetMapping("/mensajes/")
	public Page<EstablecimientoDTO> obtenerEstablecimientoConMensajes(@RequestParam int size, @RequestParam int page) {
		return obtenerEstablecimientoPorNombreConMensajes(EMPTY_STRING, size, page);
	}

	@GetMapping("")
	public Page<EstablecimientoDTO> obtenerTodosLosEstablecimientos(@RequestParam("size") int size,
			@RequestParam("page") int page) {
		return establecimientoService.obtenerTodosLosEstablecimientos(page, size);
	}

	@GetMapping("/activo/{nombreEstablecimiento}")
	public Page<EstablecimientoDTO> obtenerEstablecimientosActivos(@PathVariable String nombreEstablecimiento,
			@RequestParam("size") int size, @RequestParam("page") int page) {

		return establecimientoService.obtenerEstablecimientosActivos(nombreEstablecimiento, page, size);
	}

	@GetMapping("/activo/")
	public Page<EstablecimientoDTO> obtenerEstablecimientosActivos(@RequestParam("size") int size,
			@RequestParam("page") int page) {

		return obtenerEstablecimientosActivos(EMPTY_STRING, size, page);
	}

	@GetMapping("/nuevo/{nombreEstablecimiento}")
	public Page<EstablecimientoDTO> obtenerEstablecimientosNuevos(@PathVariable String nombreEstablecimiento,
			@RequestParam("size") int size, @RequestParam("page") int page) {

		return establecimientoService.obtenerEstablecimientosNuevos(nombreEstablecimiento, page, size);
	}

	@GetMapping("/nuevo/")
	public Page<EstablecimientoDTO> obtenerEstablecimientosNuevos(@RequestParam("size") int size,
			@RequestParam("page") int page) {

		return obtenerEstablecimientosNuevos(EMPTY_STRING, size, page);
	}

	@GetMapping("/noencontrado/{nombreEstablecimiento}")
	public Page<EstablecimientoDTO> obtenerEstablecimientosNoEncontrados(@PathVariable String nombreEstablecimiento,
			@RequestParam("size") int size, @RequestParam("page") int page) {

		return establecimientoService.obtenerEstablecimientosNoEncontrados(nombreEstablecimiento, page, size);
	}

	@GetMapping("/noencontrado/")
	public Page<EstablecimientoDTO> obtenerEstablecimientosNoEncontrados(@RequestParam("size") int size,
			@RequestParam("page") int page) {

		return obtenerEstablecimientosNoEncontrados(EMPTY_STRING, size, page);
	}

	@GetMapping("/inactivo/{nombreEstablecimiento}")
	public Page<EstablecimientoDTO> obtenerEstablecimientosInactivos(@PathVariable String nombreEstablecimiento,
			@RequestParam("size") int size, @RequestParam("page") int page) {

		return establecimientoService.obtenerEstablecimientosInactivos(nombreEstablecimiento, page, size);
	}

	@GetMapping("/inactivo/")
	public Page<EstablecimientoDTO> obtenerEstablecimientosInactivos(@RequestParam("size") int size,
			@RequestParam("page") int page) {

		return obtenerEstablecimientosInactivos(EMPTY_STRING, size, page);
	}

	@GetMapping("/sinZona")
	public List<Establecimiento> obtenerEstablecimientosSinZona() {
		return establecimientoService.obtenerEstablecimientosSinZonaAsignada();
	}

	@PutMapping(value = "/", consumes = APPLICATION_JSON_VALUE)
	public void confirmarEstablecimiento(@RequestBody ActualizarEstatosInputDTO actualizacion) {
		log.debug("Actualizando estatus de establecimiento [{}] -> [{}]", actualizacion.getId(),
				actualizacion.getEstatus());
		establecimientoService.cambiarEstatus(actualizacion.getId(), actualizacion.getEstatus());
	}

	@GetMapping(value = "/zona/{idZona}")
	public List<EstablecimientoDTO> establecimientosPorZona(@PathVariable Long idZona) {

		return establecimientoService.establecimientosDeZona(idZona);
	}

	@GetMapping(value = "/zona/{idZona}/count")
	public CountWrapper contarEstablecimientosPorZona(@PathVariable Long idZona) {

		return new CountWrapper(establecimientoService.contarEstablecimientosDeZona(idZona));
	}

}
