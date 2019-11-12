package org.bedu.testing.demo.monitoreo.entitys;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.bedu.testing.demo.monitoreo.entitys.Colonia;
import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;
import org.bedu.testing.demo.monitoreo.entitys.EstablecimientoDTO;
import org.bedu.testing.demo.monitoreo.entitys.TipoEstablecimiento;
import org.bedu.testing.demo.monitoreo.entitys.Visita;
import org.bedu.testing.demo.monitoreo.entitys.Zona;
import org.bedu.testing.demo.monitoreo.entitys.keys.ColoniaCompositeKey;
import org.bedu.testing.demo.monitoreo.enums.Estatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EstablecimientoDTOTest {

	private static final String DATE_3_STRING = "01/03/18";
	private static final LocalDateTime DATE_3 = LocalDateTime.of(2018, 3, 1, 0, 0);
	private static final LocalDateTime DATE_2 = LocalDateTime.of(2018, 2, 1, 0, 0);
	private static final LocalDateTime DATE_1 = LocalDateTime.of(2018, 1, 1, 0, 0);
	private static final String DUMMY_DESCRIPCION = "DUMMY";
	private static final long DUMMY_ID = 1L;
	private static final String CONTACTO = "Pedro Pérez";
	private static final String TELEFONO = "123456789";
	private static final String CORREO = "fake@fake.com";
	private static final String REFERENCIA = "Puerta blanca";
	private static final String CALLE = "Calle 123";
	private static final String NOMBRE = "Alguno";
	private static final long ID_ESTABLECIMIENTO = 879L;
	private static final String CODIGO_POSTAL = "00000";

	private Establecimiento establecimiento;

	@BeforeEach
	public void setup() {
		TipoEstablecimiento tipoEstablecimiento = new TipoEstablecimiento();
		tipoEstablecimiento.setIdTipoEstablecimiento(DUMMY_ID);
		tipoEstablecimiento.setDescripcion(DUMMY_DESCRIPCION);

		Zona zona = new Zona();
		zona.setIdZona(DUMMY_ID);
		zona.setDescripcion(DUMMY_DESCRIPCION);

		Colonia colonia = new Colonia();
		colonia.setDescripcion(DUMMY_DESCRIPCION);

		ColoniaCompositeKey coloniaId = new ColoniaCompositeKey(CODIGO_POSTAL, DUMMY_ID);

		colonia.setCompositeKey(coloniaId);

		Estatus estatus = Estatus.ACTIVO;

		Visita visita1 = new Visita();
		visita1.setIdVisita(1L);
		visita1.setFecha(DATE_1);

		Visita visita2 = new Visita();
		visita2.setIdVisita(2L);
		visita2.setFecha(DATE_2);

		Visita visita3 = new Visita();
		visita3.setIdVisita(3L);
		visita3.setFecha(DATE_3);

		establecimiento = new Establecimiento(ID_ESTABLECIMIENTO, NOMBRE, CALLE, REFERENCIA, TELEFONO, CORREO, CONTACTO,
				null, tipoEstablecimiento, zona, colonia, estatus, null,
				Arrays.asList(new Visita[] { visita2, visita3, visita1 }), null, null);

	}

	@Test
	@DisplayName("Determina correctametne la última visita")
	void testOf() {
		EstablecimientoDTO edto = EstablecimientoDTO.of(establecimiento);

		assertThat(edto.getUltimaVisita()).isEqualTo(DATE_3_STRING);
	}

}
