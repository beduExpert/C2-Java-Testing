package org.bedu.testing.demo.monitoreo.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import lombok.Setter;

@Configuration
public class JWTBeans {

	@Setter(onMethod_ = { @Value("${oauth2.jwt.key}") })
	private String tokenKey;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(tokenKey);

		return tokenConverter;
	}

	@Bean
	@Autowired
	public TokenStore tokenStore(JwtAccessTokenConverter jwtTokenConverter) {
		return new JwtTokenStore(jwtTokenConverter);
	}

	@Bean
	@Autowired
	public DefaultTokenServices defaultTokenServices(JwtTokenStore tokenStore, JwtAccessTokenConverter tokenConverter) {
		DefaultTokenServices tokenServices = new DefaultTokenServices();

		tokenServices.setTokenStore(tokenStore);
		tokenServices.setTokenEnhancer(tokenConverter);
		tokenServices.setSupportRefreshToken(true);

		return tokenServices;
	}

}
