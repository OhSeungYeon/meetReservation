package com.gbm.edu.api.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.gbm.edu.security.model.SecurityUser;
import com.gbm.edu.security.model.UserInfo;
import com.gbm.edu.security.service.UserService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;

//@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
    private UserService userService;

	@Autowired
    private BCryptPasswordEncoder passwordEncoder;

	private Logger log = Logger.getLogger(CustomAuthenticationProvider.class);
	
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
 	
    	String username = (String) authentication.getPrincipal();
    	String password = (String) authentication.getCredentials();
    	
    	UserInfo userInfo = (UserInfo) userService.loadUserByUsername(username);
        if(!passwordEncoder.matches(password, userInfo.getPassword())) {
            throw new BadCredentialsException(username);
        }
 
        if(!userInfo.isEnabled()) {
            throw new BadCredentialsException(username);
        }
        
        return new UsernamePasswordAuthenticationToken(username, password, userInfo.getAuthorities());

    }
 
    @Override
    public boolean supports(Class<?> authentication) {
    	return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


}
