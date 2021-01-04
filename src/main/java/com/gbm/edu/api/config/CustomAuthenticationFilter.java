package com.gbm.edu.api.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gbm.edu.security.model.SecurityUser;
import org.apache.log4j.Logger;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private Logger log = Logger.getLogger(CustomAuthenticationFilter.class);
	
	public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

    	 UsernamePasswordAuthenticationToken authRequest;
    	 
    	 try {
			SecurityUser securityUser = new ObjectMapper().readValue(request.getInputStream(), SecurityUser.class);
			authRequest = new UsernamePasswordAuthenticationToken(securityUser.getUsername(), securityUser.getPassword());
    	 } catch (IOException e) {
			throw new InputNotFoundException();
    	 }

    	 setDetails(request, authRequest);
         return this.getAuthenticationManager().authenticate(authRequest);
    
    }
	
}
