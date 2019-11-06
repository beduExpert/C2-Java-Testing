/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services.implementation;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bedu.testing.demo.monitoreo.reportes.ReporteSemanal;
import org.bedu.testing.demo.monitoreo.reportes.exporters.ReportExporter;
import org.bedu.testing.demo.monitoreo.services.ReporteSemanalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Fernando
 */
@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@Secured("IS_AUTHENTICATED_FULLY")
public class ReporteSemanalServiceImplement implements ReporteSemanalService {
	private final ReporteSemanal reporteSemanal;
	private final ReportExporter exporter;

	@Override
	public String getXLSX(LocalDate fechaInicio, LocalDate fechaFin) {

		JasperPrint print;
		try {
			print = reporteSemanal.crearReporteSemanal(fechaInicio, fechaFin);

			return exporter.exportarAFormatoExcel(print);
		} catch (JRException ex) {
			Logger.getLogger(ReporteSemanalServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;

	}

	@Override
	public String getPDF(LocalDate fechaInicio, LocalDate fechaFin) {
		JasperPrint print;
		try {
			print = reporteSemanal.crearReporteSemanal(fechaInicio, fechaFin);

			return exporter.exportarAformatoPDF(print);
		} catch (JRException ex) {
			Logger.getLogger(ReporteSemanalServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

}
