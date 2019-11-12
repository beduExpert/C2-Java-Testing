package org.bedu.testing.demo.monitoreo.services.implementation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Perfil;
import org.bedu.testing.demo.monitoreo.repository.PerfilRepository;
import org.bedu.testing.demo.monitoreo.services.implementation.PerfilServiceImplement;
import org.bedu.testing.demo.monitoreo.tools.LoggedUserAuthorities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PerfilServiceImplementTest {

	private static final List<Long> MARCHAND_PROFILES_LIST = Arrays
			.asList(new Long[] { Perfil.ADMIN_ID, Perfil.OPERATIVO_ID });

	private static final Perfil administradorMarchand = new Perfil(Perfil.ADMIN_ID, "Administrador Marchand");
	private static final Perfil operativoMarchand = new Perfil(Perfil.OPERATIVO_ID, "Operativo Marchand");
	private static final Perfil administradorRocket = new Perfil(Perfil.SUPERADMIN_ID,
			"Administrador Rocket Delivery");
	private static final Perfil promotor = new Perfil(Perfil.PROMOTOR_ID, "Promotor Marchand");

	private static final List<Perfil> PROFILES_LIST = Arrays
			.asList(new Perfil[] { administradorMarchand, operativoMarchand, administradorRocket, promotor });

	@Mock
	private PerfilRepository repository;

	@Mock
	private LoggedUserAuthorities authority;

	@InjectMocks
	private PerfilServiceImplement service;

	@BeforeEach
	public void setup() {

		MockitoAnnotations.initMocks(this);

		given(repository.findAll()).willReturn(PROFILES_LIST);

	}

	@Test
	@DisplayName("Returns empty list if non admins")
	public void filtersOutAllForNonAdmins() {

		given(authority.isMarchandAdmin()).willReturn(false);
		given(authority.isRocketAdmin()).willReturn(false);

		Iterable<Perfil> resultList = service.obtenerTodosLosPerfiles();

		// then
		assertThat(resultList.iterator().hasNext()).isFalse();

	}

	@Test
	@DisplayName("Returns full list for Administrador Rocket")
	public void returnsFullListForAdminRocket() {

		given(authority.isMarchandAdmin()).willReturn(false);
		given(authority.isRocketAdmin()).willReturn(true);

		Iterable<Perfil> resultIterable = service.obtenerTodosLosPerfiles();

		List<Perfil> resultList = new ArrayList<>();

		resultIterable.forEach(p -> resultList.add(p));

		// then
		assertThat(resultList.size()).isEqualTo(PROFILES_LIST.size());

	};

	@Test
	@DisplayName("Returns reduced list for Adminsitrador Marchand")
	public void returnsReducedListForAdminMarchand() {

		given(authority.isMarchandAdmin()).willReturn(true);
		given(authority.isRocketAdmin()).willReturn(false);

		Iterable<Perfil> resultIterable = service.obtenerTodosLosPerfiles();

		List<Perfil> resultList = new ArrayList<>();

		resultIterable.forEach(p -> resultList.add(p));

		// then
		assertThat(resultList.size()).isEqualTo(MARCHAND_PROFILES_LIST.size());

	};

}
