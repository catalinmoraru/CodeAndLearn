package com.devplant.introduction.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.annotation.PostConstruct;

@Profile("secure")
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthorizationConfiguration {

	@PostConstruct
	protected void postConstruct(){

	}
}
