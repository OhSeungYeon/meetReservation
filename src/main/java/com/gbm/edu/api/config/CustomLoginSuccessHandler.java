package com.gbm.edu.api.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.gbm.edu.security.model.SecurityUser;
import com.gbm.edu.security.model.UserInfo;
import com.gbm.edu.security.service.UserService;

import edu.emory.mathcs.backport.java.util.Arrays;

import org.apache.log4j.Logger;

public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	private Logger log = Logger.getLogger(CustomLoginSuccessHandler.class);
	
	@Autowired
	private UserService userService;
	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
				
		clearAuthenticationAttributes(request);

		HttpSession session = request.getSession();
		session.setAttribute("userId", authentication.getPrincipal());
		
		//getRedirectStrategy().sendRedirect(request, response, "http://localhost:8081/api/edu/readView");
		response.sendRedirect("http://localhost:8081/api/edu/readView");
		
    }


}
