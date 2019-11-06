package org.bedu.testing.demo.monitoreo.services.implementation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bedu.testing.demo.monitoreo.constants.MonitoreoProfiles;
import org.bedu.testing.demo.monitoreo.entitys.Perfil;
import org.bedu.testing.demo.monitoreo.entitys.Usuario;
import org.bedu.testing.demo.monitoreo.repository.UsuarioRepository;
import org.bedu.testing.demo.monitoreo.services.implementation.UsuarioServiceImplement;
import org.bedu.testing.demo.monitoreo.tools.LoggedUserAuthorities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

class UsuarioServiceImplementTest {

	private static final Perfil perfilAM = new Perfil(1L, "Administrador Marchand");
	private static final Perfil perfilOM = new Perfil(2L, "Operativo Marchand");
	private static final Perfil perfilAR = new Perfil(3L, "Administrador Rocket Delivery");
	private static final Perfil perfilP = new Perfil(4L, "Promotor");

	private static final Usuario u1 = new Usuario(1L, "xx", "xx", "xx", "xx", "xx", perfilAM, null);
	private static final Usuario u2 = new Usuario(2L, "xx", "xx", "xx", "xx", "xx", perfilAM, null);
	private static final Usuario u3 = new Usuario(3L, "xx", "xx", "xx", "xx", "xx", perfilOM, null);
	private static final Usuario u4 = new Usuario(4L, "xx", "xx", "xx", "xx", "xx", perfilOM, null);
	private static final Usuario u5 = new Usuario(5L, "xx", "xx", "xx", "xx", "xx", perfilAR, null);
	private static final Usuario u6 = new Usuario(6L, "xx", "xx", "xx", "xx", "xx", perfilAR, null);
	private static final Usuario u7 = new Usuario(7L, "xx", "xx", "xx", "xx", "xx", perfilP, null);
	private static final Usuario u8 = new Usuario(8L, "xx", "xx", "xx", "xx", "xx", perfilP, null);

	private static final PageRequest PAGE_REQUEST = PageRequest.of(0, 1);

	private static List<Usuario> fullList = new ArrayList<>(
			Arrays.asList(new Usuario[] { u1, u2, u3, u4, u5, u6, u7, u8 }));

	private static List<Usuario> reducedList = new ArrayList<>(Arrays.asList(new Usuario[] { u1, u2, u3, u4 }));

	@Mock
	private UsuarioRepository repository;

	@Mock
	private LoggedUserAuthorities authority;

	@InjectMocks
	private UsuarioServiceImplement service;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);

		given(repository.searchByString("%%", PAGE_REQUEST)).willReturn(new PageImpl<>(fullList));
		given(repository.searchByStringAndProfileID("%%", MonitoreoProfiles.MONITOREO_PROFILES, PAGE_REQUEST))
				.willReturn(new PageImpl<>(reducedList));
	}

	@Test
	@DisplayName("Returns full list for rocket admin")
	public void test() {

		given(authority.isRocketAdmin()).willReturn(true);
		given(authority.isMarchandAdmin()).willReturn(false);

		Page<Usuario> perfiles = service.obtenerUsuarios("", 0, 1);

		then(repository).should(times(1)).searchByString("%%", PAGE_REQUEST);
		assertThat(perfiles.getContent().size()).isEqualTo(fullList.size());
	}

	@Test
	@DisplayName("Returns empty list for non admin")
	public void returnEptyListForNonAdmin() {

		given(authority.isRocketAdmin()).willReturn(false);
		given(authority.isMarchandAdmin()).willReturn(false);

		Page<Usuario> perfiles = service.obtenerUsuarios("", 0, 1);

		assertThat(perfiles.getContent().size()).isEqualTo(0);

	}

	@Test
	@DisplayName("Rerturns reduced list for marchand admin")
	public void returnsReducedListForMarchandAdmin() {

		given(authority.isRocketAdmin()).willReturn(false);
		given(authority.isMarchandAdmin()).willReturn(true);

		Page<Usuario> perfiles = service.obtenerUsuarios("", 0, 1);

		then(repository).should(times(1)).searchByStringAndProfileID("%%", MonitoreoProfiles.MONITOREO_PROFILES,
				PAGE_REQUEST);
		assertThat(perfiles.getContent().size()).isEqualTo(reducedList.size());
	}

}
