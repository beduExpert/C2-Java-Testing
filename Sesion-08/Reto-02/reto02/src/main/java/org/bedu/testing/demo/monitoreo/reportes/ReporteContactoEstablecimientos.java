package org.bedu.testing.demo.monitoreo.reportes;

import java.sql.SQLException;
import java.util.HashMap;

import javax.sql.DataSource;

import org.bedu.testing.demo.monitoreo.excepciones.MonitoreoException;
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

@Slf4j
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@Component
public class ReporteContactoEstablecimientos {
	private final DataSource dataSource;

	@Setter(onMethod_ = { @Value("${rd.marchand.monitoreo.reportes.contacto}") })
	private String nombreReporte;

	@Setter(onMethod_ = { @Value("${rd.marchand.monitoreo.reportes.base}") })
	private String basePath;

	@Setter(onMethod_ = { @Value("${rd.marchand.monitoreo.reportes.logo}") })
	private String logoPath;

	private JasperReport reporte;

	public JasperPrint crearReporte() throws JRException {

		if (reporte == null) {
			reporte = (JasperReport) JRLoader
					.loadObject(getClass().getClassLoader().getResourceAsStream(basePath + nombreReporte));
		}

		try {
			return JasperFillManager.fillReport(reporte, new HashMap<>(), dataSource.getConnection());
		} catch (JRException ex) {
			log.error("JRException al llenar el reporte de contactos de establecimiento", ex);
			throw new MonitoreoException("JRException al llenar el reporte de contactos de establecimiento", ex);
		} catch (SQLException ex) {
			log.error("SQLException al llenar el reporte de contactos de establecimiento", ex);
		}

		return null;

	}

}
