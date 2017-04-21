package com.daw.themadridnews.preferences;

import com.daw.themadridnews.favourite.Favourite;
import com.daw.themadridnews.files.FileUploadCommons;
import com.daw.themadridnews.requests.ApiDataUser;
import com.daw.themadridnews.user.User;
import com.daw.themadridnews.user.UserService;
import com.daw.themadridnews.utils.Message;
import com.daw.themadridnews.webconfig.Config;
import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<Object> get() {
        User u = userService.getLoggedUser();
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    /**
     * Modificar configuracion del usuario
     */
    @JsonView(UserService.UserDetails.class)
    @RequestMapping(value="/ajustes", method=RequestMethod.PUT)
    public ResponseEntity<Object> saveData(@RequestBody ApiDataUser r) {
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
		
		Favourite fav = r.getFavourites();
		fav.setId( u.getFavourites().getId() );
		u.setFavourites(fav);
		
		u.setSex(r.getSex());
		u.setCity(r.getCity());
		u.setCountry(r.getCountry());
		u.setPhone(r.getPhoneNumber());
		u.setDescription(r.getDescription());
		u.setPersonalWeb(r.getPersonalWeb());
		
		u = userService.save(u);
		
		return new ResponseEntity<>(u, HttpStatus.OK);
    }
    
    @JsonView(UserService.UserDetails.class)
    @RequestMapping(value="/ajustes/imagen", method=RequestMethod.POST)
    public ResponseEntity<Object> saveImage(@RequestParam(name="file") MultipartFile file) {
    	User userLogged = userService.getLoggedUser();
    	
    	boolean result = FileUploadCommons.saveImage( file, config.getPathImgUsers(), String.valueOf(userLogged.getId()) );
    	if(!result)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(userLogged, HttpStatus.OK);
    }
}
