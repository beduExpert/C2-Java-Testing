package org.bedu.testing.demo.monitoreo.services.implementation;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.bedu.testing.demo.monitoreo.entitys.BitacoraJornada;
import org.bedu.testing.demo.monitoreo.entitys.Perfil;
import org.bedu.testing.demo.monitoreo.entitys.Usuario;
import org.bedu.testing.demo.monitoreo.repository.BitacoraJornadaRepository;
import org.bedu.testing.demo.monitoreo.services.UsuarioService;
import org.bedu.testing.demo.monitoreo.services.implementation.BitacoraJornadaServiceImplement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class BitacoraJornadaServiceImplementTest {
	private static final BitacoraJornada BITACORA_JORNADA = new BitacoraJornada();
	private static final LocalDate TODAY = LocalDate.now();
	private static final LocalDateTime INICIO = TODAY.atStartOfDay();
	private static final LocalDateTime FIN = TODAY.plusDays(1).atStartOfDay();
	private static final Usuario USUARIO = new Usuario(1L, "x", "x", "x", "x", "x", new Perfil(4L, "PROMOTOR"), null);

	@Mock
	private BitacoraJornadaRepository repository;

	@Mock
	private UsuarioService usuarioService;

	@InjectMocks
	private BitacoraJornadaServiceImplement service;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);

		when(usuarioService.getLoggedUser()).thenReturn(USUARIO);
	}

	@Test
	@DisplayName("Returns existing object")
	void returnExistingObject() {
		given(repository.findOneByUsuarioAndInicioJornadaBetween(USUARIO, INICIO, FIN)).willReturn(BITACORA_JORNADA);

		// when
		BitacoraJornada bitacoraJornada = service.agregarNuevaBitacoraJornada();

		then(bitacoraJornada).isNotNull();
		verify(repository, times(0)).save(any());
	}

	@Test
	@DisplayName("Returns new object after saving it")
	void returnNewObject() {
		given(repository.findOneByUsuarioAndInicioJornadaBetween(USUARIO, INICIO, FIN)).willReturn(null);
		given(repository.save(any())).willAnswer(AdditionalAnswers.returnsFirstArg());

		// when
		BitacoraJornada bitacoraJornada = service.agregarNuevaBitacoraJornada();

		then(bitacoraJornada).isNotNull();
		verify(repository, times(1)).save(any());
	}
}
