package com.daw.themadridnews.webconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.daw.themadridnews.user.UserRepositoryAuthenticationProvider;

@Configuration
@Order(1)
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    protected UserRepositoryAuthenticationProvider authProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.antMatcher("/api/**");
		
		// URLs that need authentication to access to it
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/articulo/{id}/comentario").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/api/ajustes").hasAnyRole("USER", "EDITOR", "PUBLICIST", "ADMIN");
		
		http.authorizeRequests().antMatchers("/api/publicista/**").hasAnyRole("PUBLICIST", "ADMIN");
		http.authorizeRequests().antMatchers("/api/editor/**").hasAnyRole("EDITOR", "ADMIN");
		http.authorizeRequests().antMatchers("/api/administrador/**").hasAnyRole("ADMIN");
		
		// Other URLs can be accessed without authentication
		http.authorizeRequests().anyRequest().permitAll();

		// CSRF protection
		http.csrf().disable();
		//http.csrf();

		// Use Http Basic Authentication
		http.httpBasic();
		
		// Login configuration
		http.formLogin().loginProcessingUrl("/api/login");
		http.formLogin().successHandler((rq, rs, a) -> {});

		// Logout configuration
		http.logout().logoutSuccessHandler((rq, rs, a) -> {}); // No redirect on logout
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Database authentication provider
		auth.authenticationProvider(authProvider);
	}
}
