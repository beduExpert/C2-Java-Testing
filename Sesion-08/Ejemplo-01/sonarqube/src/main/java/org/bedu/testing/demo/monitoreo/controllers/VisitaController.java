/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.bedu.testing.demo.monitoreo.entitys.VisitaDetailDTO;
import org.bedu.testing.demo.monitoreo.enums.ResultadoVisita;
import org.bedu.testing.demo.monitoreo.services.VisitaService;
import org.bedu.testing.demo.monitoreo.tools.VisitaDetailFactory;
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

/**
 *
 * @author Fernando
 */
@RequestMapping(value = "/visita", produces = APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@Slf4j
public class VisitaController {

	private final VisitaService visitaService;

	@Data
	private static class VisitaInputDTO {
		private Long idEstablecimiento;
		private String comentario;
		private ResultadoVisita resultado;
		
	}

	@PostMapping(value = "/", consumes = APPLICATION_JSON_VALUE)
	public boolean registrarVisita(@RequestBody VisitaInputDTO datosVisita) {
		
		log.debug("Registrando visita para el establecimiento [{}]", datosVisita.idEstablecimiento);

		try {
			visitaService.agregarVisita(datosVisita.idEstablecimiento, datosVisita.comentario, datosVisita.resultado);
			return true;
		} catch (Exception er) {
			log.error("error agregar vista. " + er);
			return false;
		}

	}

	@GetMapping(value = "/promotor/{idPromotor}")
	public Iterable<VisitaDetailDTO> visitasDeUsuarioDelDia(@PathVariable long idPromotor) {

		return VisitaDetailFactory.fromRutaAsignada(visitaService.visitasDeUsuarioDelDia(idPromotor));

	}

}
