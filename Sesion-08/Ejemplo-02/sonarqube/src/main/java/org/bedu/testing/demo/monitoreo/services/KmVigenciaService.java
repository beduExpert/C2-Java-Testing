package org.bedu.testing.demo.monitoreo.services;

import java.time.LocalDate;

import org.bedu.testing.demo.monitoreo.entitys.KmVigencia;

public interface KmVigenciaService {

	KmVigencia activeValue();

	KmVigencia activeValue(LocalDate date);
}
