/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services;

import java.time.LocalDate;

/**
 *
 * @author Fernando
 */
public interface ReporteSemanalService {

	String getXLSX(LocalDate fechaInicio, LocalDate fechaFin);

	String getPDF(LocalDate fechaInicio, LocalDate fechaFin);

}
