package org.bedu.testing.demo.monitoreo.reportes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.bedu.testing.demo.monitoreo.entitys.KmVigencia;
import org.bedu.testing.demo.monitoreo.reportes.ReporteDiario;
import org.bedu.testing.demo.monitoreo.services.KmVigenciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

class ReporteDiarioTest {

	private static final String DIARIO = "diario.jasper";
	private static final String DIARIO_SUB_REPORTE = "subreporteDiario_promotor.jasper";
	private static final String BASE_PATH = "reportes/";
	private static final String LOGO_PATH = "reportes/img/logo.png";
	private static final KmVigencia FACTOR = new KmVigencia(1L, LocalDate.now(), 20.0);

	@Mock
	private DataSource dataSource;

	@Mock
	private KmVigenciaService vigenciaService;

	@InjectMocks
	private ReporteDiario reporteDiario;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);

		reporteDiario.setNombreReporte(DIARIO);
		reporteDiario.setNombreSubReporte(DIARIO_SUB_REPORTE);
		reporteDiario.setBasePath(BASE_PATH);
		reporteDiario.setLogoPath(LOGO_PATH);

		when(vigenciaService.activeValue(any())).thenReturn(FACTOR);
	}

	@Test
	@DisplayName("Genera el archivo")
	void test() throws JRException {

		JasperPrint jp = reporteDiario.crearReporteDiario(LocalDate.now());

		assertThat(jp).isNotNull();

	}

}
