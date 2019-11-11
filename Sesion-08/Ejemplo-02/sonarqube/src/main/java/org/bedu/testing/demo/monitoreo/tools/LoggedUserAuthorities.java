package org.bedu.testing.demo.monitoreo.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class LoggedUserAuthorities {

	private final SecurityContextHolderWrapper securityContextHolderWrapper;

	@Setter(onMethod_ = { @Value("${rd.marchand.monitoreo.perfiles.adminMarchand}") })
	private String adminMarchandProfileName;

	@Setter(onMethod_ = { @Value("${rd.marchand.monitoreo.perfiles.adminRocket}") })
	private String adminRocketProfileName;
	private Pattern patternAdminMarchand;
	private Pattern patternAdminRocket;

	private Matcher getRocketMatcher(String loggedUserAuthority) {
		if (patternAdminRocket == null) {
			log.debug("Rocket Delivery matcher is [{}]", adminRocketProfileName.toUpperCase());
			patternAdminRocket = Pattern.compile(adminRocketProfileName.toUpperCase());
		}

		return patternAdminRocket.matcher(loggedUserAuthority);
	}

	private Matcher getMarchandMatcher(String loggedUserAuthority) {
		if (patternAdminMarchand == null) {
			log.debug("Marchand matcher is [{}]", adminMarchandProfileName.toUpperCase());
			patternAdminMarchand = Pattern.compile(adminMarchandProfileName.toUpperCase());
		}

		return patternAdminMarchand.matcher(loggedUserAuthority);
	}

	public boolean isRocketAdmin() {

		Matcher matcher = getRocketMatcher(securityContextHolderWrapper.getAuthority());
		return matcher.find();

	}

	public boolean isMarchandAdmin() {

		Matcher matcher = getMarchandMatcher(securityContextHolderWrapper.getAuthority());
		return matcher.find();

	}

	public String getLoggedUsername() {

		return securityContextHolderWrapper.getUsername();

	}

}
