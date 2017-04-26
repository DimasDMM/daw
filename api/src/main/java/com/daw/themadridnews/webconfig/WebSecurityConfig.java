package com.daw.themadridnews.webconfig;
import com.daw.themadridnews.user.UserRepositoryAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    protected UserRepositoryAuthenticationProvider authProvider;

    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	// Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/portada").permitAll();
        http.authorizeRequests().antMatchers("/portada/**").permitAll();
        http.authorizeRequests().antMatchers("/categoria/**").permitAll();
        http.authorizeRequests().antMatchers("/privacidad").permitAll();
        http.authorizeRequests().antMatchers("/terminos-de-uso").permitAll();

        http.authorizeRequests().antMatchers("/imagen/**").permitAll();
        http.authorizeRequests().antMatchers("/anuncio/**").permitAll();
        
        http.authorizeRequests().antMatchers("/buscar").permitAll();

        http.authorizeRequests().antMatchers("/registro").permitAll();
        http.authorizeRequests().antMatchers("/registro/**").permitAll();
        http.authorizeRequests().antMatchers("/loginerror").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();

        http.authorizeRequests().antMatchers("/ajustes").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/ajustes/**").hasAnyRole("USER");
        
        http.authorizeRequests().antMatchers("/editor/**").hasAnyRole( "EDITOR", "ADMIN");
        http.authorizeRequests().antMatchers("/publicista/**").hasAnyRole( "PUBLICIST", "ADMIN");
        http.authorizeRequests().antMatchers("/administrador/**").hasAnyRole( "ADMIN");
        
        // Login form
        http.formLogin().loginPage("/portada");
        http.formLogin().usernameParameter("email");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/portada");
        http.formLogin().failureUrl("/loginError");

		http.csrf().disable();

        // Logout
        //http.logout().logoutUrl("/logout");
        //http.logout().logoutSuccessUrl("/portada");
        
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }
}
