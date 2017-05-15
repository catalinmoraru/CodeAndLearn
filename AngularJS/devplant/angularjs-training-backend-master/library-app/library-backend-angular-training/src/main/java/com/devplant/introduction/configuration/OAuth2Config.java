package com.devplant.introduction.configuration;

import com.devplant.introduction.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.*;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.io.IOException;

@Configuration
@EnableResourceServer
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).accessTokenConverter(jwtAccessTokenConverter());
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("devplant-backend").secret("devplant-backend-secret")
				.authorizedGrantTypes("refresh_token", "password").scopes("openid").accessTokenValiditySeconds(7200)
				.refreshTokenValiditySeconds(0).autoApprove(true);
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() throws IOException {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(securityProperties.getPrivateKey());
		converter.setVerifierKey(securityProperties.getPublicKey());
		return converter;
	}

}