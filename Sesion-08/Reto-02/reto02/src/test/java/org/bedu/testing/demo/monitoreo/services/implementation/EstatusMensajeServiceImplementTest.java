package org.bedu.testing.demo.monitoreo.services.implementation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;

import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;
import org.bedu.testing.demo.monitoreo.entitys.Mensaje;
import org.bedu.testing.demo.monitoreo.repository.MensajeRepository;
import org.bedu.testing.demo.monitoreo.services.implementation.EstatusMensajeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class EstatusMensajeServiceImplementTest {

	private static final long ID_ESTABLECIMIENTO = 123L;
	private static final Establecimiento ESTABLECIMIENTO = new Establecimiento(ID_ESTABLECIMIENTO, "Papelería",
			"calle 123", "", "1234567890", "fake@fake.com", "Pedrito", null, null, null, null, null, null, null, null,
			null);

	@Mock
	private MensajeRepository repository;

	@InjectMocks
	private EstatusMensajeServiceImpl service;

	@BeforeEach
	public void setup() {

		MockitoAnnotations.initMocks(this);

	}

	@Test
	@DisplayName("Returns false when there are no messages")
	public void testReadMessagesFalse() {

		given(repository.findByEstablecimientoAndLeido(ESTABLECIMIENTO, false)).willReturn(new ArrayList<Mensaje>());

		boolean response = service.mensajesNoLeidosDeEstablecimiento(ESTABLECIMIENTO);

		assertThat(response).isFalse();

	}

	@Test
	@DisplayName("Returns true when there are no messages")
	public void testReadMessagesTrue() {

		given(repository.findByEstablecimientoAndLeido(ESTABLECIMIENTO, false))
				.willReturn(Arrays.asList(new Mensaje[] { new Mensaje(), new Mensaje() }));

		boolean response = service.mensajesNoLeidosDeEstablecimiento(ESTABLECIMIENTO);

		assertThat(response).isTrue();

	}
}
