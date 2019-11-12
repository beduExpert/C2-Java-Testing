/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Fernando
 */
@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

	private final AuthenticationManager authenticationManager;
	private final AccessTokenConverter accessTokenConverter;
	private final TokenStore tokenStore;
	private final UserDetailsService userDetailsService;

	private final PasswordEncoder passwordEncoder;

	@Setter(onMethod_ = { @Value("${rd.marchand.monitoreo.client}") })
	private String client;

	@Setter(onMethod_ = { @Value("${rd.marchand.monitoreo.password}") })
	private String secret;

	@Setter(onMethod_ = { @Value("${rd.marchand.monitoreo.resource}") })
	private String resource;

	@Setter(onMethod_ = { @Value("${rd.marchand.monitoreo.tokenValidity}") })
	private int tokenValidity;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		//@formatter:off
		clients
		.inMemory()
		.withClient(client)
		.secret(passwordEncoder.encode(secret))
		.accessTokenValiditySeconds(tokenValidity)
		.scopes("read", "write")
		.authorizedGrantTypes("password", "refresh_token")
		.resourceIds(resource);
		//@formatter:on

	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		//@formatter:off
		endpoints
		.accessTokenConverter(accessTokenConverter)
		.authenticationManager(authenticationManager)
		.tokenStore(tokenStore)
		.userDetailsService(userDetailsService);
		//@formatter:on

	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.checkTokenAccess("permitAll()");
	}
	
	

}
