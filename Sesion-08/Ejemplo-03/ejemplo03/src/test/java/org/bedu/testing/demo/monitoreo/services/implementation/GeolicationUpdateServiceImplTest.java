package org.bedu.testing.demo.monitoreo.services.implementation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Colonia;
import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;
import org.bedu.testing.demo.monitoreo.entitys.Municipio;
import org.bedu.testing.demo.monitoreo.entitys.keys.ColoniaCompositeKey;
import org.bedu.testing.demo.monitoreo.entitys.keys.MunicipioCompositeKey;
import org.bedu.testing.demo.monitoreo.enums.Estatus;
import org.bedu.testing.demo.monitoreo.services.EstablecimientoService;
import org.bedu.testing.demo.monitoreo.services.implementation.GeolocationUpdateServiceImpl;
import org.bedu.testing.demo.monitoreo.tools.google.GoogleGeolocator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class GeolicationUpdateServiceImplTest {
	private static final String CALLE_Y_NUMERO = "calle 123";
	private static final BigDecimal LATITUD = new BigDecimal("10.34");
	private static final BigDecimal LONGITUD = new BigDecimal("-50.34");

	private static final Long ID_MUNICIPIO = 14L;
	private static final Long ID_ESTADO = 94L;

	private static final MunicipioCompositeKey MUN_KEY = new MunicipioCompositeKey(ID_MUNICIPIO, ID_ESTADO);

	private static final Municipio MUNICIPIO = new Municipio(MUN_KEY, "yyy", null);

	private static final Long ID_COLONIA = 85L;
	private static final String CP = "00000";
	private static final ColoniaCompositeKey COL_KEY = new ColoniaCompositeKey(CP, ID_COLONIA);

	private static final Colonia COLONIA = new Colonia(COL_KEY, "xxx", MUNICIPIO);

	private static final Establecimiento EST_1 = new Establecimiento(1L, "", CALLE_Y_NUMERO, "", "", "", "", null, null,
			null, COLONIA, Estatus.ACTIVO, null, null, null, null);

	private static final Establecimiento EST_2 = new Establecimiento(1L, "", CALLE_Y_NUMERO, "", "", "", "", null, null,
			null, COLONIA, Estatus.PENDIENTE, null, null, LATITUD, LONGITUD);

	private static final Establecimiento EST_3 = new Establecimiento(1L, "", CALLE_Y_NUMERO, "", "", "", "", null, null,
			null, COLONIA, Estatus.INACTIVO, null, null, LATITUD, LONGITUD);

	private static final List<Establecimiento> LISTA_ESTABLECIMIENTOS = Arrays
			.asList(new Establecimiento[] { EST_1, EST_2, EST_3 });

	@Mock
	private EstablecimientoService establecimientoService;

	@Mock
	private GoogleGeolocator googleService;

	@InjectMocks
	private GeolocationUpdateServiceImpl service;

	@BeforeEach
	public void setup() throws Exception{
		MockitoAnnotations.initMocks(this);

		given(establecimientoService.obtenerTodosLosEstablecimientos()).willReturn(LISTA_ESTABLECIMIENTOS);
		given(googleService.geolocate(any())).willAnswer(AdditionalAnswers.returnsFirstArg());
	}

	@Test
	@DisplayName("Actualiza todos los establecimientos que no están inactivos cuando fuerza la actualización")
	void updatesAllButInactiveWhenForced() throws Exception{
		// when
		service.updateGeolocation(true);

		verify(googleService, times(1)).geolocate(EST_1);
		verify(googleService, times(1)).geolocate(EST_2);
	}

	@Test
	@DisplayName("Actualiza solo los establecimientos no inactivos y sin coordenadas cuando no se fuerza la actualización")
	void updatesOnlyWithEmptyLocationWhenNotForced() throws Exception{
		// when
		service.updateGeolocation(false);

		verify(googleService, times(1)).geolocate(EST_1);
	}

	@Test
	@DisplayName("Si hay un error lo omite y pasa al siguiente")
	void skipsErrors() throws Exception{
		given(googleService.geolocate(EST_2)).willThrow(new RuntimeException());

		// when
		service.updateGeolocation(true);

		verify(googleService, times(1)).geolocate(EST_1);
	}
}
