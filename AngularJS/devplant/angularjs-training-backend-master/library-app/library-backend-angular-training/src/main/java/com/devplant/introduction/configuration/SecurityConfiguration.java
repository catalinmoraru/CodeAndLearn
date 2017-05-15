package com.devplant.introduction.configuration;

import com.devplant.introduction.security.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Slf4j
@Configuration
@EnableWebSecurity
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class SecurityConfiguration extends ResourceServerConfigurerAdapter {

	@Autowired
	private UserService userService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().formLogin().disable().httpBasic().disable().authorizeRequests().antMatchers("/**")
				.permitAll();
	}

	@Bean
	public DaoAuthenticationProvider jpaDaoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

}
