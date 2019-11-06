package org.bedu.testing.demo.monitoreo.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.bedu.testing.demo.monitoreo.services.EstablecimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/establecimiento")
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@Slf4j
public class FotografiaController {

	private final EstablecimientoService establecimientoService;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	private static class FotografiaIODTO {
		private String img;
	}

	@PostMapping(value = "/{idEstablecimiento}/img", consumes = APPLICATION_JSON_VALUE)
	public boolean cambiarFotografia(@PathVariable Long idEstablecimiento,
			@RequestBody FotografiaIODTO fotografiaBytes) {

		log.debug("Se solicita actualización de fotografía");
		log.debug("Se recibe objeto? [{}]", (fotografiaBytes != null));
		log.debug("Se recibe img? [{}]", (fotografiaBytes.getImg() != null));

		return establecimientoService.cambiarFotografia(idEstablecimiento, fotografiaBytes.getImg());
	}

	@GetMapping(value = "/{idEstablecimiento}/img", produces = APPLICATION_JSON_VALUE)
	public FotografiaIODTO obtenerFotografia(@PathVariable Long idEstablecimiento) {
		return new FotografiaIODTO(establecimientoService.obtenerFotografia(idEstablecimiento));
	}
}
