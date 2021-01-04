package com.gbm.edu.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gbm.edu.security.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        //super.configure(web); // 아무런 작업을 하지 않음
        // 스프링 시큐리티의 필터 연결을 설정하기 위한 오버라이딩이다.
		web.ignoring().antMatchers("/css/**", "/js/**", "**/images/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 인터셉터로 요청을 안전하게 보호하는 방법을 설정하기 위한 오버라이딩이다.
    	    	
    	http
        .cors().and()
		.csrf().disable()
        .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
	    .authorizeRequests()
	    	.antMatchers("/**").permitAll()
		    .antMatchers("/**/css/**").permitAll()
			.antMatchers("/**/images/**").permitAll()
			.antMatchers("/**/js/**").permitAll()
	    	.antMatchers("/api/login/**").permitAll()
	    	.antMatchers("/api/edu/**").hasRole("USER")
	    	//.antMatchers("/api/edu/admin/**").hasAuthority("ROLE_ADMIN")
	    	//.anyRequest().authenticated()   	
	    	
	     .and()
         .formLogin()
        	.loginPage("/api/login/login")
        	.loginProcessingUrl("/api/login/login_process")
        	.successHandler(customLoginSuccessHandler())
    		.failureUrl("/api/login/login")
	    	.usernameParameter("username")
			.passwordParameter("password")
			
		.and()			
    	.logout()
    		.logoutUrl("/api/login/logout_process")
    		.logoutSuccessUrl("/api/login/login")
    		.deleteCookies("JESSIONID")
    		.invalidateHttpSession(true)
    		.clearAuthentication(true);
    	//.and()
    	//.addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);	
	    http.
	    	httpBasic();
    	   	
    }

	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // 사용자 세부 서비스를 설정하기 위한 오버라이딩이다.
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
		auth.authenticationProvider(customAuthenticationProvider());
    }   
    
    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
		return new CustomAuthenticationProvider();
	}

	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
      return new BCryptPasswordEncoder();
    }
	
	 @Bean
	 public AuthenticationSuccessHandler customLoginSuccessHandler() {
		 return new CustomLoginSuccessHandler();
	 }
	 
	 @Bean
	 public UserService userService() {
		 return new UserService();
	 }

	 /*
	@Bean
	public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
		CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
		customAuthenticationFilter.setFilterProcessesUrl("/api/login/loginFilter");
		customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());
		customAuthenticationFilter.afterPropertiesSet();
		return customAuthenticationFilter;
	}*/
	 
}