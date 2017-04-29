package com.daw.themadridtimes.webconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.daw.themadridtimes.user.UserRepositoryAuthenticationProvider;

@Configuration
@Order(1)
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    protected UserRepositoryAuthenticationProvider authProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.antMatcher("/api/**");

		http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/**").permitAll();

		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/registro/paso_2").hasAnyRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/articulo/{id}/comentario").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/api/ajustes").hasAnyRole("USER", "EDITOR", "PUBLICIST", "ADMIN");
		
		http.authorizeRequests().antMatchers("/api/publicista/**").hasAnyRole("PUBLICIST", "ADMIN");
		http.authorizeRequests().antMatchers("/api/editor/**").hasAnyRole("EDITOR", "ADMIN");
		http.authorizeRequests().antMatchers("/api/administrador/**").hasAnyRole("ADMIN");

		// CSRF protection
		http.csrf().disable();

		// Use Http Basic Authentication
		http.httpBasic();
		
		// Login configuration
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
