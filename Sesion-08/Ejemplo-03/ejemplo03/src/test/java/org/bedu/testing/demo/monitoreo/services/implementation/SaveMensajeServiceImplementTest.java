package org.bedu.testing.demo.monitoreo.services.implementation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;
import org.bedu.testing.demo.monitoreo.entitys.Mensaje;
import org.bedu.testing.demo.monitoreo.entitys.Usuario;
import org.bedu.testing.demo.monitoreo.repository.MensajeRepository;
import org.bedu.testing.demo.monitoreo.services.EstablecimientoService;
import org.bedu.testing.demo.monitoreo.services.UsuarioService;
import org.bedu.testing.demo.monitoreo.services.implementation.SaveMensajeServiceImplement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class SaveMensajeServiceImplementTest {

	private static final long ID_ESTABLECIMIENTO = 123L;
	private static final String MESSAGE_STRING = "Hola mundo";
	private static final Establecimiento ESTABLECIMIENTO = new Establecimiento(ID_ESTABLECIMIENTO, "Papelería",
			"calle 123", "", "1234567890", "", "Pedrito", null, null, null, null, null, null, null, null, null);
	private static final Usuario USUARIO = new Usuario(432L, "admin", "password", "pedro", "pérez", "garcía", null, null);

	@Mock
	private MensajeRepository mensajeReporsitory;

	@Mock
	private EstablecimientoService establecimientoService;

	@Mock
	private UsuarioService usuarioService;

	@InjectMocks
	private SaveMensajeServiceImplement service;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);

		when(mensajeReporsitory.save(any(Mensaje.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());
	}

	@Test
	@DisplayName("Creates and saves new message")
	public void testNuevoMensaje() {
		given(establecimientoService.obtenerEstablecimientoPorId(ID_ESTABLECIMIENTO)).willReturn(ESTABLECIMIENTO);
		given(usuarioService.getLoggedUser()).willReturn(USUARIO);

		Mensaje m = service.nuevoMensaje(MESSAGE_STRING, ID_ESTABLECIMIENTO);

		assertThat(m.getMensaje()).isEqualTo(MESSAGE_STRING);
		assertThat(m.getFecha()).isCloseTo(LocalDateTime.now(), within(500, ChronoUnit.MILLIS));
		assertThat(m.getUsuario()).isEqualTo(USUARIO);
		assertThat(m.getEstablecimiento()).isEqualTo(ESTABLECIMIENTO);
		assertThat(m.isLeido()).isFalse();
	}

}
