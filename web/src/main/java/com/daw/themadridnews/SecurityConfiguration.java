package com.daw.themadridnews;
import com.daw.themadridnews.user.UserRepositoryAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public UserRepositoryAuthenticationProvider authenticationProvider;

	@Override
    protected void configure(HttpSecurity http) throws Exception {

    	// Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/loginerror").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();

        http.authorizeRequests().antMatchers("/article").permitAll();
        http.authorizeRequests().antMatchers("/index").permitAll();
        http.authorizeRequests().antMatchers("/madrid").permitAll();
        http.authorizeRequests().antMatchers("/culture").permitAll();
        http.authorizeRequests().antMatchers("/spain").permitAll();
        http.authorizeRequests().antMatchers("/technology").permitAll();
        http.authorizeRequests().antMatchers("/sports").permitAll();
        http.authorizeRequests().antMatchers("/world").permitAll();
        http.authorizeRequests().antMatchers("/privacy_and_policy").permitAll();
        http.authorizeRequests().antMatchers("/terms_and_conditions").permitAll();
        // Private pages (all other pages)
        http.authorizeRequests().antMatchers("/user-settings").hasAnyRole("USER", "EDITOR", "ADMIN");
        http.authorizeRequests().antMatchers("/user-settings.html").hasAnyRole("USER", "EDITOR", "ADMIN");
        http.authorizeRequests().antMatchers("/ads_create").hasAnyRole("ADMIN", "ADVERTISING");
        http.authorizeRequests().antMatchers("/ads_create.html").hasAnyRole("ADMIN", "ADVERTISING");
        http.authorizeRequests().antMatchers("/ads_list").hasAnyRole("ADMIN", "ADVERTISING");
        http.authorizeRequests().antMatchers("/ads_list.html").hasAnyRole("ADMIN", "ADVERTISING");
        
        http.authorizeRequests().antMatchers("/editor/articulo/{id}").hasAnyRole( "EDITOR", "ADMIN");
        http.authorizeRequests().antMatchers("/editor/articulo/{id}/publicar").hasAnyRole( "EDITOR", "ADMIN");
        http.authorizeRequests().antMatchers("/editor/articulo/{id}/ocultar").hasAnyRole( "EDITOR", "ADMIN");
        http.authorizeRequests().antMatchers("/editor/articulo/{id}/eliminar").hasAnyRole( "EDITOR", "ADMIN");
        http.authorizeRequests().antMatchers("/editor/articulo/nuevo").hasAnyRole( "EDITOR", "ADMIN");
        http.authorizeRequests().antMatchers("/editor/articulo/listado").hasAnyRole( "EDITOR", "ADMIN");
        http.authorizeRequests().antMatchers("/editor/articulo/listado/{page}").hasAnyRole( "EDITOR", "ADMIN");
        http.authorizeRequests().antMatchers("/editor/articulo/listado/publicado").hasAnyRole( "EDITOR", "ADMIN");
        http.authorizeRequests().antMatchers("/editor/articulo/listado/ocultado").hasAnyRole( "EDITOR", "ADMIN");
        http.authorizeRequests().antMatchers("/editor/articulo/listado/eliminado").hasAnyRole( "EDITOR", "ADMIN");

        // Login form
        http.formLogin().loginPage("/index");
        http.formLogin().loginPage("/spain");
        http.formLogin().usernameParameter("email");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/index");
        http.formLogin().failureUrl("/hola_template");

        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/index");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        // Database authentication provider
        auth.authenticationProvider(authenticationProvider);
    }
}
