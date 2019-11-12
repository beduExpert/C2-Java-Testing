package org.bedu.testing.demo.monitoreo.services.implementation;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.bedu.testing.demo.monitoreo.entitys.Colonia;
import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;
import org.bedu.testing.demo.monitoreo.entitys.Perfil;
import org.bedu.testing.demo.monitoreo.entitys.RutaAsignada;
import org.bedu.testing.demo.monitoreo.entitys.TipoEstablecimiento;
import org.bedu.testing.demo.monitoreo.entitys.Usuario;
import org.bedu.testing.demo.monitoreo.entitys.Visita;
import org.bedu.testing.demo.monitoreo.entitys.Zona;
import org.bedu.testing.demo.monitoreo.enums.Estatus;
import org.bedu.testing.demo.monitoreo.repository.RutaAsignadaRepository;
import org.bedu.testing.demo.monitoreo.services.UsuarioService;
import org.bedu.testing.demo.monitoreo.services.implementation.RutaAsignadaServiceImplement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RutaAsignadaServiceImplementTest {

	private static final RutaAsignada RUTA = new RutaAsignada();
	private static final TipoEstablecimiento TIPO = new TipoEstablecimiento();
	private static final Zona ZONA = new Zona();
	private static final Colonia COLONIA = new Colonia();
	private static final Estatus ESTATUS = Estatus.ACTIVO;
	private static final Establecimiento EST_1 = new Establecimiento(1L, "e1", "cyn", "ref", "1234567890", "", "x",
			null, TIPO, ZONA, COLONIA, ESTATUS, null, null, null, null);
	private static final Visita VISITA_1A = new Visita();
	private static final Visita VISITA_1B = new Visita();
	private static final Establecimiento EST_2 = new Establecimiento(2L, "e2", "cyn", "ref", "1234567890", "", "x",
			null, TIPO, ZONA, COLONIA, ESTATUS, null, null, null, null);
	private static final Visita VISITA_2A = new Visita();
	private static final Establecimiento EST_3 = new Establecimiento(3L, "e3", "cyn", "ref", "1234567890", "", "x",
			null, TIPO, ZONA, COLONIA, ESTATUS, null, null, null, null);
	private static final Visita VISITA_3A = new Visita();

	private static final Perfil PERFIL = new Perfil();
	private static final Usuario USUARIO = new Usuario(1L, "xxx", "ddd", "xxx", "xxx", "xxx", PERFIL, null);

	@Mock
	private RutaAsignadaRepository repository;
	@Mock
	private UsuarioService usuarioService;

	@InjectMocks
	private RutaAsignadaServiceImplement service;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);

		when(usuarioService.getLoggedUser()).thenReturn(USUARIO);
		when(usuarioService.obtenerUsuarioPorId(1L)).thenReturn(USUARIO);

		PERFIL.setIdPerfil(Perfil.PROMOTOR_ID);
		USUARIO.setPerfil(PERFIL);

		RUTA.setEstablecimiento(Arrays.asList(new Establecimiento[] { EST_1, EST_2, EST_3 }));

	}

	@Test
	@DisplayName("Filtra visitas del día")
	void filtraUno() {
		given(repository.findByUsuarioAndFecha(USUARIO, LocalDate.now())).willReturn(RUTA);

		VISITA_1A.setFecha(LocalDateTime.now());
		VISITA_1B.setFecha(LocalDateTime.now().minusDays(7));
		EST_1.setVisitas(Arrays.asList(new Visita[] { VISITA_1B, VISITA_1A }));

		VISITA_2A.setFecha(LocalDateTime.now().minusDays(7));
		EST_2.setVisitas(Arrays.asList(new Visita[] { VISITA_2A }));

		VISITA_3A.setFecha(LocalDateTime.now().minusDays(7));
		EST_3.setVisitas(Arrays.asList(new Visita[] { VISITA_3A }));

		// when
		RutaAsignada ruta = service.buscarRutaDelDia();

		then(ruta.getEstablecimiento().size()).isEqualTo(2);

	}

	@Test
	@DisplayName("Filtra visitas del día y regresa lista vacía si ya están termiados")
	void filtraTodo() {
		given(repository.findByUsuarioAndFecha(USUARIO, LocalDate.now())).willReturn(RUTA);

		VISITA_1A.setFecha(LocalDateTime.now());
		EST_1.setVisitas(Arrays.asList(new Visita[] { VISITA_1A }));

		VISITA_2A.setFecha(LocalDateTime.now());
		EST_2.setVisitas(Arrays.asList(new Visita[] { VISITA_2A }));

		VISITA_3A.setFecha(LocalDateTime.now());
		EST_3.setVisitas(Arrays.asList(new Visita[] { VISITA_3A }));

		// when
		RutaAsignada ruta = service.buscarRutaDelDia();

		then(ruta.getEstablecimiento().size()).isEqualTo(0);

	}

	@Test
	@DisplayName("Filtra visitas del día y regresa lista completa si no hay visitas")
	void filtraNada() {
		RUTA.setEstablecimiento(new ArrayList<>());
		given(repository.findByUsuarioAndFecha(USUARIO, LocalDate.now())).willReturn(RUTA);

		// when
		RutaAsignada ruta = service.buscarRutaDelDia();

		then(ruta.getEstablecimiento().size()).isEqualTo(0);

	}
}
