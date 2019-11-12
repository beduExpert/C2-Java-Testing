package org.bedu.testing.demo.monitoreo.tools.google;

import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;

public interface GoogleGeolocator {

	/**
	 * Busca las coordenadas de un establecimiento en el servicio de geocodificación
	 * de google.
	 * 
	 * Se envía al servicio las coordenadas de dos puntos que delimitan el área a la
	 * que se dará preferencia en caso de resultados múltiples.
	 * 
	 * Esos puntos son el inferior izquierdo (SW) y el superior derecho (NE).
	 * 
	 * @param establecimiento
	 * @param latNE
	 * @param lngNE
	 * @param latSW
	 * @param lngSW
	 * @return
	 */
	Establecimiento geolocate(Establecimiento establecimiento, Double latSW, Double lngSW, Double latNE, Double lngNE) throws InterruptedException;

	Establecimiento geolocate(Establecimiento establecimiento) throws InterruptedException;
}
