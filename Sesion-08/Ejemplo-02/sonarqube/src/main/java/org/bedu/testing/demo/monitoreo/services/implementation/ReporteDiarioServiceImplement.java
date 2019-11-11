/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services.implementation;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bedu.testing.demo.monitoreo.reportes.ReporteDiario;
import org.bedu.testing.demo.monitoreo.reportes.exporters.ReportExporter;
import org.bedu.testing.demo.monitoreo.services.ReporteDiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Fernando
 */
@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@Slf4j
@Secured("IS_AUTHENTICATED_FULLY")
public class ReporteDiarioServiceImplement implements ReporteDiarioService {

	private final ReporteDiario reporteDiario;

	private final ReportExporter exporter;

	@Override
	public String getXLSX(LocalDate fecha) {
		log.debug("Solicitando reporte XLSX de fecha {}", fecha);
		try {
			JasperPrint print = reporteDiario.crearReporteDiario(fecha);
			return exporter.exportarAFormatoExcel(print);
		} catch (JRException ex) {
			Logger.getLogger(ReporteDiarioServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
		}

		return null;
	}

	@Override
	public String getPDF(LocalDate fecha) {
		log.debug("Solicitando reporte PDF de fecha {}", fecha);
		try {
			JasperPrint print = reporteDiario.crearReporteDiario(fecha);
			return exporter.exportarAformatoPDF(print);
		} catch (JRException ex) {
			Logger.getLogger(ReporteDiarioServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
		}

		return null;
	}

}
