package com.daw.themadridnews;

import com.daw.themadridnews.requests.ApiSignup;
import com.daw.themadridnews.user.User;
import com.daw.themadridnews.user.UserService;
import com.daw.themadridnews.utils.Message;
import com.daw.themadridnews.webconfig.Config;
import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class WebRestController {
    
    @Autowired protected Config config;
    @Autowired protected UserService userService;
    
    
    /**
	 * BORRAR ESTE METODO CUANDO SE TERMINE DE TESTEAR
	 */
	@RequestMapping(value="/csrf", method=RequestMethod.GET)
	public ResponseEntity<Object> subscribe(HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		
		if(token == null && request.getSession() != null)
			token = (CsrfToken) request.getSession().getAttribute("_csrf");
		
		String csrf;
		if(token != null) {
			csrf = token.getToken();
		} else {
			csrf = "vacio";
		}
		
		return new ResponseEntity<>( csrf, HttpStatus.OK );
	}
	/***********/

    @RequestMapping(value= {"/logout"})
    public ResponseEntity<Object> logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @JsonView(UserService.UserDetails.class)
    @RequestMapping(value="/login")
    public ResponseEntity<Object> login() {
    	User u = userService.getLoggedUser();
    	if(u == null)
    		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    	
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @JsonView(UserService.UserDetails.class)
    @RequestMapping(value="/registro", method=RequestMethod.POST)
	public ResponseEntity<Object> registerNew(@RequestBody ApiSignup r) {
    	Message message = r.validation();
    	if(message.getCode() != 0)
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    	
    	User user = new User();
    	user.setAlias(r.getAlias());
    	user.setName( r.getName() );
    	user.setLastname( r.getLastname() );
    	user.setEmail( r.getEmail() );
    	user.setFavourites( r.getFavourites() );
    	user.setPasswordHash( r.getPassword() );
    	user.getRoles().add("ROLE_USER");
        
    	user = userService.save(user);
    	userService.setLoggedUser(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}