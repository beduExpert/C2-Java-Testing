package org.bedu.testing.demo.monitoreo.services.implementation;

import java.time.LocalDate;

import org.bedu.testing.demo.monitoreo.reportes.ReporteContactoEstablecimientos;
import org.bedu.testing.demo.monitoreo.reportes.ReporteVisitas;
import org.bedu.testing.demo.monitoreo.reportes.exporters.ReportExporter;
import org.bedu.testing.demo.monitoreo.services.ReportesService;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@Service
@Secured("IS_AUTHENTICATED_FULLY")
public class ReporteServiceImpl implements ReportesService {

	private final ReporteContactoEstablecimientos reporteContactos;
	private final ReporteVisitas reporteVisitas;

	private final ReportExporter exporter;

	@Override
	public String reporteContactos() {

		JasperPrint print;
		try {
			print = reporteContactos.crearReporte();
			return exporter.exportarAFormatoExcel(print);
		} catch (JRException ex) {
			Log.error("Error al crear reporte", ex);
		}
		return null;

	}

	@Override
	public String reporteVisitas(LocalDate fecha) {

		JasperPrint print;
		try {
			print = reporteVisitas.crearReporte(fecha);
			return exporter.exportarAFormatoExcel(print);
		} catch (JRException ex) {
			Log.error("Error al crear reporte de visitas", ex);
		}
		return null;
	}

}
