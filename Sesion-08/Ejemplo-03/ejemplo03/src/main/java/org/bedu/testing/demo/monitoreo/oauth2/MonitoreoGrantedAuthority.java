package org.bedu.testing.demo.monitoreo.oauth2;

import org.bedu.testing.demo.monitoreo.entitys.Perfil;
import org.springframework.security.core.GrantedAuthority;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MonitoreoGrantedAuthority implements GrantedAuthority {

	private static final long serialVersionUID = 20181025L;

	private final Perfil perfil;

	@Override
	public String getAuthority() {
		return String.format("ROLE_%s", perfil.getDescripcion().toUpperCase());
	}

}
