package com.jwt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.jwt.filter.CustomAuthenticationFilter;
import com.jwt.filter.CustomAuthorizationFilter;





@Configuration @EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {




	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;



	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	
	private  final String[] PUBLIC_MATCHERS= {
            "/api/login/**",
            "/api/token/refresh/**"
    };

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
		customAuthenticationFilter.setFilterProcessesUrl("/api/login");
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http
		.authorizeRequests()
        .antMatchers(PUBLIC_MATCHERS)
        .permitAll()
        .antMatchers("/user/**").hasAuthority("ROLE_USER")
        .antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
        .anyRequest()
        .authenticated();
		http.addFilter(customAuthenticationFilter);
		http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		http.csrf().disable().cors().disable();
          
		
		
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}

	
}

