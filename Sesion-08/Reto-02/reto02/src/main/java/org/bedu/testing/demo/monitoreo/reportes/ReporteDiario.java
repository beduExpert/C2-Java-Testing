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
 * @author Fernando
 */
@Component
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@Slf4j
public class ReporteDiario {

	private final DataSource dataSource;
	private final KmVigenciaService kmVigenciaService;

	@Setter(onMethod_ = { @Value("${rd.marchand.monitoreo.reportes.diario}") })
	private String nombreReporte;

	@Setter(onMethod_ = { @Value("${rd.marchand.monitoreo.reportes.subreporteDiario}") })
	private String nombreSubReporte;

	@Setter(onMethod_ = { @Value("${rd.marchand.monitoreo.reportes.base}") })
	private String basePath;

	@Setter(onMethod_ = { @Value("${rd.marchand.monitoreo.reportes.logo}") })
	private String logoPath;

	JasperReport reporte;

	public JasperPrint crearReporteDiario(LocalDate fecha) throws JRException {

		if (reporte == null) {
			log.debug("Cargando reporte desde [{} {}]", basePath, nombreReporte);
			reporte = (JasperReport) JRLoader
					.loadObject(getClass().getClassLoader().getResourceAsStream(basePath + nombreReporte));
		}

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("fecha", fecha.format(DateTimeFormatter.ISO_LOCAL_DATE));
		parameters.put("factorEstablecimiento", kmVigenciaService.activeValue(fecha).getValorKilometros());
		parameters.put("logoPath", logoPath);

		if (log.isDebugEnabled()) {
			for (Map.Entry<String, Object> e : parameters.entrySet()) {
				log.debug("Parámetro [{}] : [{}]", e.getKey(), e.getValue());
			}
		}

		try {
			return JasperFillManager.fillReport(reporte, parameters, dataSource.getConnection());
		} catch (JRException ex) {
			String message = String.format("JRException al llenar el reporte de fecha [%s]", fecha);
			log.error(message, ex);
			throw new MonitoreoException(message, ex);
		} catch (SQLException ex) {
			String message = String.format("SQLException al llenar el reporte de fecha [%s]", fecha);
			log.error(message, ex);
		}

		return null;

	}

}
