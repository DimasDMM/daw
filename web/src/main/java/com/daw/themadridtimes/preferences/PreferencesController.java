package com.daw.themadridtimes.preferences;

import com.daw.themadridtimes.favourite.Favourite;
import com.daw.themadridtimes.files.FileUploadCommons;
import com.daw.themadridtimes.requests.FormUserFavourites;
import com.daw.themadridtimes.requests.FormUserPass;
import com.daw.themadridtimes.requests.FormUserPersonal;
import com.daw.themadridtimes.user.User;
import com.daw.themadridtimes.user.UserService;
import com.daw.themadridtimes.utils.Message;
import com.daw.themadridtimes.webconfig.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PreferencesController {

    @Autowired protected UserService userService;
    @Autowired protected Config config;


    @RequestMapping(value="/ajustes", method=RequestMethod.GET)
    public ModelAndView userSettings(Model model, HttpServletRequest request) {
        User userLogged = userService.getLoggedUser();
        model.addAttribute("user_name", userLogged.getName());
        model.addAttribute("user_lastname", userLogged.getLastname());
        model.addAttribute("user_alias", (userLogged.getAlias() == null ? "" : userLogged.getAlias()) );
        model.addAttribute("user_country", (userLogged.getCountry() == null ? "" : userLogged.getCountry()) );
        model.addAttribute("user_city", (userLogged.getCity() == null ? "" : userLogged.getCity()) );
        model.addAttribute("user_phone", (userLogged.getPhone() == null ? "" : userLogged.getPhone()) );
        model.addAttribute("user_description", (userLogged.getDescription() == null ? "" : userLogged.getDescription()) );
        model.addAttribute("user_url", (userLogged.getPersonalWeb() == null ? "" : userLogged.getPersonalWeb()) );
        model.addAttribute("user_email", userLogged.getEmail());
        model.addAttribute("user_id", userLogged.getId());

        model.addAttribute("user_sex_m", (userLogged.getSex() != null && userLogged.getSex() == 'm') );
        model.addAttribute("user_sex_f", (userLogged.getSex() != null && userLogged.getSex() == 'f') );
        model.addAttribute("user_sex_o", (userLogged.getSex() != null && userLogged.getSex() == 'o') );
        
        Favourite favs = userLogged.getFavourites();
        model.addAttribute("fav_world", favs.getWorld());
        model.addAttribute("fav_spain", favs.getSpain());
        model.addAttribute("fav_madrid", favs.getMadrid());
        model.addAttribute("fav_sports", favs.getSports());
        model.addAttribute("fav_technology", favs.getTechnology());
        model.addAttribute("fav_culture", favs.getCulture());
        
		return new ModelAndView("preferences");
    }

    @RequestMapping(value="/ajustes/guardar/personal", method=RequestMethod.POST)
    public ModelAndView userSettingsSavePersonal(Model model, HttpServletRequest request, FormUserPersonal r) {
    	Message message = r.validation();
    	if(message.getCode() != 0) {
    		model.addAttribute("message", message);
    		return userSettings(model, request);
    	}
    	
    	User userLogged = userService.getLoggedUser();
        
    	userLogged.setName( r.getName() );
    	userLogged.setLastname( r.getLastname() );
    	userLogged.setAlias( r.getAlias() );
    	userLogged.setEmail( r.getEmail() );
    	userLogged.setSex( r.getSex() );
    	userLogged.setPhone( r.getPhone() );
    	userLogged.setCity( r.getCity() );
    	userLogged.setCountry( r.getCountry() );
    	userLogged.setDescription( r.getDescription() );
    	userLogged.setPersonalWeb( r.getUrl() );
    	
    	userService.save(userLogged);
    	
    	message.setCode(0);
    	message.setMessage("Los cambios han sido guardados correctamente");
    	model.addAttribute("message", message);

		return userSettings(model, request);
    }

    @RequestMapping(value="/ajustes/guardar/contrasena", method=RequestMethod.POST)
    public ModelAndView userSettingsSavePass(Model model, HttpServletRequest request, FormUserPass r) {
    	Message message = r.validation();
    	if(message.getCode() != 0) {
    		model.addAttribute("message", message);
    		return userSettings(model, request);
    	}
    	
        User oldUser = userService.getLoggedUser();

        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        if(!bcrypt.matches( r.getPass_now(), oldUser.getPasswordHash())){
        	message.setCode(1);
        	message.setMessage("La contraseña actual no coincide con la que tiene en estos momentos");
        	model.addAttribute("message", message);
    		return userSettings(model, request);
        }
        
        // Guardar contraseña
        oldUser.setPasswordHash(r.getPass_new());
        userService.save(oldUser);
        
    	message.setCode(0);
    	message.setMessage("La nueva contraseña ha sido guardada correctamente");
    	model.addAttribute("message", message);
    	
		return userSettings(model, request);
    }

    @RequestMapping(value="/ajustes/guardar/favoritos", method=RequestMethod.POST)
    public ModelAndView userSettingsSaveFavourites(Model model, HttpServletRequest request, FormUserFavourites r) {
    	Message message = r.validation();
    	
    	User userLogged = userService.getLoggedUser();
    	Favourite favourite = userLogged.getFavourites();
    	
    	favourite.setMadrid( r.get("madrid") );
    	favourite.setSpain( r.get("spain") );
    	favourite.setWorld( r.get("world") );
    	favourite.setSports( r.get("sports") );
    	favourite.setTechnology( r.get("technology") );
    	favourite.setCulture( r.get("culture") );
    	
    	userLogged.setFavourites(favourite);
    	userService.save(userLogged);

    	message.setCode(0);
    	message.setMessage("Los cambios han sido guardados correctamente");
    	model.addAttribute("message", message);
    	
		return userSettings(model, request);
    }

    @RequestMapping(value="/ajustes/guardar/imagen", method=RequestMethod.POST)
    public ModelAndView userSettingsSaveFavourites(Model model, HttpServletRequest request, @RequestParam("file") MultipartFile file) {
    	Message message = new Message();

        User userLogged = userService.getLoggedUser();
    	FileUploadCommons.saveImage( file, config.getPathImgUsers(), String.valueOf(userLogged.getId()) );

    	message.setCode(0);
    	message.setMessage("La imagen de perfil ha sido guardada correctamente");
    	model.addAttribute("message", message);
    	
		return userSettings(model, request);
    }
}
