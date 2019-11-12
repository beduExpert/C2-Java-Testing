/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.reportes;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.bedu.testing.demo.monitoreo.excepciones.MonitoreoException;
import org.bedu.testing.demo.monitoreo.services.KmVigenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author cypunk
 */
@Component
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@Slf4j
public class ReporteSemanal {
	private final DataSource dataSource;
	private final KmVigenciaService kmVigenciaService;

	@Setter(onMethod_ = { @Value("${rd.marchand.monitoreo.reportes.semanal}") })
	private String nombreReporte;

	@Setter(onMethod_ = { @Value("${rd.marchand.monitoreo.reportes.base}") })
	private String basePath;

	@Setter(onMethod_ = { @Value("${rd.marchand.monitoreo.reportes.logo}") })
	private String logoPath;

	private JasperReport reporte;

	public JasperPrint crearReporteSemanal(LocalDate fechaInicio, LocalDate fechaFin) throws JRException {

		if (reporte == null) {
			reporte = (JasperReport) JRLoader
					.loadObject(getClass().getClassLoader().getResourceAsStream(basePath + nombreReporte));
		}

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("fechaInicio", fechaInicio.format(DateTimeFormatter.ISO_LOCAL_DATE));
		parameters.put("fechaFin", fechaFin.format(DateTimeFormatter.ISO_LOCAL_DATE));
		parameters.put("factorEstablecimientos", kmVigenciaService.activeValue(fechaInicio).getValorKilometros());
		parameters.put("logoPath", logoPath);

		try {
			return JasperFillManager.fillReport(reporte, parameters, dataSource.getConnection());
		} catch (JRException ex) {
			String message = String.format("JRException al llenar el reporte de fechas [%s - %s]", fechaInicio,
					fechaFin);
			log.error(message, ex);
			throw new MonitoreoException(message, ex);
		} catch (SQLException ex) {
			String message = String.format("SQLException al llenar el reporte de fechas [%s - %s]", fechaInicio, fechaFin);
			log.error(message, ex);
		}

		return null;

	}

}
