package com.gbm.edu.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		//@formatter:off
		/*
		 * setting for common
		 */
		http
			.csrf().disable();
		http
			.headers().frameOptions().disable();
		
		/*
		 * Access Policy
		 */
		http.authorizeRequests()
					
			// None Access Policy
			.antMatchers("/api/**")
			.permitAll()
		
			/*
			 * Http Method Access Policy
			 * (Http Method Policy는 URL Policy를 정의한 다름에 정의해야 한다. 만약, 위에 넣을 시 모든 URL이 permitAll 되는 현상이 발생한다.)
			 */
			.antMatchers(HttpMethod.OPTIONS)	// Preflight Request(CORS) 위한 OPTIONS 허용 
			.permitAll()
			.antMatchers(HttpMethod.GET)
			.permitAll()
			.antMatchers(HttpMethod.POST)
			.permitAll()
			.antMatchers(HttpMethod.DELETE)
			.permitAll()
			// ---------------------------------------------------
			
			.anyRequest().authenticated();
			
		//@formatter:on
	}
}