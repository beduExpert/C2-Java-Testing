package org.bedu.testing.demo.monitoreo.services.implementation;

import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;
import org.bedu.testing.demo.monitoreo.enums.Estatus;
import org.bedu.testing.demo.monitoreo.services.EstablecimientoService;
import org.bedu.testing.demo.monitoreo.services.GeolocationUpdateService;
import org.bedu.testing.demo.monitoreo.tools.google.GoogleGeolocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@Slf4j
@Secured({ "ROLE_ADMINISTRADOR ROCKET DELIVERY" })
public class GeolocationUpdateServiceImpl implements GeolocationUpdateService {

	@Data
	@AllArgsConstructor
	private class ErrorWrapper {
		private Establecimiento establecimiento;
		private Exception exception;
	}

	private final EstablecimientoService establecimientoService;
	private final GoogleGeolocator googleService;

	@Override
	public void updateGeolocation(Boolean force) {

		long contador = 0;
		long establecimientosConError = 0;

		Iterable<Establecimiento> establecimientos = establecimientoService.obtenerTodosLosEstablecimientos();

		for (Establecimiento e : establecimientos) {

			boolean noGeolocation = (e.getLatitud() == null || e.getLongitud() == null);

			if (!e.getEstatus().equals(Estatus.INACTIVO) && (force || noGeolocation)) {
				try {
					establecimientoService.actualizarEstablecimiento(googleService.geolocate(e));
					contador++;
				} catch (Exception err) {
					establecimientosConError++;
					log.error("{}", new ErrorWrapper(e, err));
				}
			}
		}

		log.debug("Se actualizaron [{}] establecimientos", contador);
		log.debug("Se encontraron [{}] establecimientos con error", establecimientosConError);

	}

}
