package org.bedu.testing.demo.monitoreo.services;

import java.time.LocalDate;

public interface ReportesService {

	String reporteContactos();

	String reporteVisitas(LocalDate fecha);

}
