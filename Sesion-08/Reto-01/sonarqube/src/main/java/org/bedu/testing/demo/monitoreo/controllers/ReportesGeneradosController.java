/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_PDF_VALUE;

import java.time.LocalDate;

import org.bedu.testing.demo.monitoreo.excepciones.MonitoreoException;
import org.bedu.testing.demo.monitoreo.services.ReporteDiarioService;
import org.bedu.testing.demo.monitoreo.services.ReporteSemanalService;
import org.bedu.testing.demo.monitoreo.services.ReportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Fernando
 */
@RequestMapping(value = "/reporte", produces = APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class ReportesGeneradosController {

	private static final String XLSX_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	private final ReporteSemanalService reporteSemanalService;
	private final ReporteDiarioService reporteDiarioService;
	private final ReportesService reporteContactoService;

	@AllArgsConstructor
	@Data
	private static class ReportWrapper {
		private String content;
		private String contentType;
	}

	@GetMapping("/semanal")
	public ReportWrapper generarReporteSemanal(
			@RequestParam("inicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
			@RequestParam("fin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin,
			@RequestParam("formato") String formato) {

		switch (formato) {
		case "pdf":
			return new ReportWrapper(reporteSemanalService.getPDF(fechaInicio, fechaFin), APPLICATION_PDF_VALUE);
		case "xlsx":
			return new ReportWrapper(reporteSemanalService.getXLSX(fechaInicio, fechaFin), XLSX_CONTENT_TYPE);
		default:
			throw new MonitoreoException("Formato no reconocido. Las opciones son pdf o xlsx");
		}
	}

	@GetMapping("/diario")
	public ReportWrapper generarReporteDiario(
			@RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha,
			@RequestParam("formato") String formato) {

		switch (formato) {
		case "pdf":
			return new ReportWrapper(reporteDiarioService.getPDF(fecha), APPLICATION_PDF_VALUE);
		case "xlsx":
			return new ReportWrapper(reporteDiarioService.getXLSX(fecha), XLSX_CONTENT_TYPE);
		default:
			throw new MonitoreoException("Formato no reconocido. Las opciones son pdf o xlsx");
		}

	}

	@GetMapping("/contactos")
	public ReportWrapper generarReporteContacto() {

		return new ReportWrapper(reporteContactoService.reporteContactos(), XLSX_CONTENT_TYPE);

	}

	@GetMapping("/visitas")
	public ReportWrapper generarReporteVisitas(
			@RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha) {

		return new ReportWrapper(reporteContactoService.reporteVisitas(fecha), XLSX_CONTENT_TYPE);

	}
}
