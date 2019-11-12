package org.bedu.testing.demo.monitoreo.controllers;

import org.bedu.testing.demo.monitoreo.entitys.DatosEstablecimientoDTO;
import org.bedu.testing.demo.monitoreo.services.EstablecimientoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/establecimiento")
@RequiredArgsConstructor
public class ActualizacionDatosController {
	private final EstablecimientoService establecimientoService;

	@PostMapping(value = "/{idEstablecimiento}/datos")
	public void actualizarServicioEstablecimiento(@PathVariable Long idEstablecimiento,
			@RequestBody DatosEstablecimientoDTO datos) {
		establecimientoService.actualizarServicioEstablecimiento(idEstablecimiento, datos.getCorreo(),
				datos.getTelefono(), datos.getTipoEstablecimiento());
	}

	@GetMapping("/{idEstablecimiento}/datos")
	public DatosEstablecimientoDTO obtenerEstablecimientoDatos(
			@PathVariable("idEstablecimiento") Long idEstablecimiento) {
		return establecimientoService.obtenerDatosEstablecimiento(idEstablecimiento);
	}
}
