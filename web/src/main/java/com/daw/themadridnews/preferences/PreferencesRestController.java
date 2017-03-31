package com.daw.themadridnews.preferences;

import com.daw.themadridnews.requests.ApiDataUser;
import com.daw.themadridnews.user.User;
import com.daw.themadridnews.user.UserService;
import com.daw.themadridnews.utils.Message;
import com.daw.themadridnews.webconfig.Config;
import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class PreferencesRestController {

    @Autowired
    protected UserService userService;
    
    @Autowired
    protected Config config;


    /**
     * Obtener configuracion actual del usuario logeado
     */
    @JsonView(UserService.UserDetails.class)
    @RequestMapping(value="/ajustes", method=RequestMethod.GET)
    public ResponseEntity<Object> get(Model model, HttpServletRequest request) {
        User u = userService.getLoggedUser();
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    /**
     * Modificar configuracion del usuario
     */
    @JsonView(UserService.UserDetails.class)
    @RequestMapping(value="/ajustes", method=RequestMethod.PUT)
    public ResponseEntity<Object> saveData(ApiDataUser r, @RequestParam(name="file", required=false) MultipartFile file) {
    	Message message;
        User u = userService.getLoggedUser();
		
		// Validacion
		message = r.validation();
		if(message.getCode() != 0) return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		
		// Guardar datos
		u.setAlias(r.getAlias());
		u.setName(r.getName());
		u.setLastname(r.getLastname());
		u.setEmail(r.getEmail());
		
		u.setRoles(r.getRoles());
		u.setFavourites(u.getFavourites());
		
		u.setSex(r.getSex());
		u.setCity(r.getCity());
		u.setCountry(r.getCountry());
		u.setPhoneNumber(r.getPhoneNumber());
		u.setDescription(r.getDescription());
		u.setPersonalWeb(r.getPersonalWeb());
		
		u = userService.save(u);
		
		return new ResponseEntity<>(u, HttpStatus.OK);
    }
}
