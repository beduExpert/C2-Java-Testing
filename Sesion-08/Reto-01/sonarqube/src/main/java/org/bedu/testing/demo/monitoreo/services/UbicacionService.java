package org.bedu.testing.demo.monitoreo.services;

import java.math.BigDecimal;

import org.bedu.testing.demo.monitoreo.entitys.Ubicacion;

public interface UbicacionService {

	void saveLocation(BigDecimal latitud, BigDecimal longitud);

	Ubicacion getLocation(Long idPromotor);

}
