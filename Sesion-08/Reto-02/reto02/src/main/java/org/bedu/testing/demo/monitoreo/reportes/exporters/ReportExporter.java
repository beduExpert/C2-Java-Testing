/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.reportes.exporters;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

import org.bedu.testing.demo.monitoreo.excepciones.MonitoreoException;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

/**
 *
 * @author Fernando
 */
@Component
@Slf4j
public class ReportExporter {

	private JRXlsxExporter xlsxExporter = new JRXlsxExporter();
	private JRPdfExporter pdfExporter = new JRPdfExporter();

	public String exportarAFormatoExcel(JasperPrint report) {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		xlsxExporter.setExporterInput(new SimpleExporterInput(report));
		xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		try {
			xlsxExporter.exportReport();
		} catch (JRException e) {
			String message = "Error al crear XLSX";
			log.error(message, e);
			throw new MonitoreoException(message, e);
		}

		return new String(Base64.getEncoder().encode(outputStream.toByteArray()));
	}

	public String exportarAformatoPDF(JasperPrint report) {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		pdfExporter.setExporterInput(new SimpleExporterInput(report));
		pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		try {
			pdfExporter.exportReport();
		} catch (JRException e) {
			String message = "Error al crear PDF";
			log.error(message, e);
			throw new MonitoreoException(message, e);
		}

		return new String(Base64.getEncoder().encode(outputStream.toByteArray()));
	}

}
