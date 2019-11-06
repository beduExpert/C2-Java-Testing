package org.bedu.testing.demo.monitoreo.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class SecurityContextHolderWrapper {

	public String getAuthority() {

		SecurityContext context = SecurityContextHolder.getContext();

		return context.getAuthentication().getAuthorities().iterator().next().getAuthority();

	}

	public String getUsername() {

		return SecurityContextHolder.getContext().getAuthentication().getName();

	}

}
