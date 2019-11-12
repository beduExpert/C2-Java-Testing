package org.bedu.testing.demo.monitoreo.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.maps.GeoApiContext;
import com.google.maps.model.ComponentFilter;
import com.google.maps.model.LatLng;

@Configuration
public class GoogleAPIConfiguration {
	// Coordenadas extremas de México tomadas de INEGI
	// http://cuentame.inegi.org.mx/territorio/coordenadas.aspx
	// ADVERTENCIA: El cuadro resultante abarca secciones de países colindantes.
	private static final double SW_LAT = 11.968611;
	private static final double SW_LNG = -122.17028;
	private static final double NE_LAT = 32.718333;
	private static final double NE_LNG = -84.641667;

	@Bean
	public GeoApiContext geoApiContext(@Value("${rd.marchand.monitoreo.google.apikey}") String apiKey) {
		return new GeoApiContext.Builder().apiKey(apiKey).build();
	}

	@Bean(name = "countryFilter")
	public ComponentFilter countryComponentFilter() {
		return ComponentFilter.country("MX");
	}

	@Bean(name = "defaultSW")
	public LatLng defaultSouthWestBundle() {
		return new LatLng(SW_LAT, SW_LNG);
	}

	@Bean(name = "defaultNE")
	public LatLng defaultNorthEastBundle() {
		return new LatLng(NE_LAT, NE_LNG);
	}

}
