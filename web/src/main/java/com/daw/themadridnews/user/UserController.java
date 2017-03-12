package com.daw.themadridnews.user;

import com.daw.themadridnews.Config;
import com.daw.themadridnews.favourite.Favourite;
import com.daw.themadridnews.files.FileUploadController;
import com.daw.themadridnews.requests.FormUserPass;
import com.daw.themadridnews.requests.FormUserPersonal;
import com.daw.themadridnews.requests.Validator;
import com.daw.themadridnews.utils.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class UserController {

    //Attributes
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserComponent userComponent;
    
    @Autowired
    private Config config;


    //Requests
    @RequestMapping(value="/ajustes", method=RequestMethod.GET)
    public String userSettings(Model model, HttpServletRequest request) {
    	config.setPageParams(model, request);
    	
        User user = userComponent.getLoggedUser();
        model.addAttribute("user_name", user.getName());
        model.addAttribute("user_lastname", user.getLastName());
        model.addAttribute("user_alias", (user.getAlias() == null ? "" : user.getAlias()) );
        model.addAttribute("user_country", (user.getCountry() == null ? "" : user.getCountry()) );
        model.addAttribute("user_city", (user.getCity() == null ? "" : user.getCity()) );
        model.addAttribute("user_phone", (user.getPhoneNumber() == null ? "" : user.getPhoneNumber()) );
        model.addAttribute("user_description", (user.getDescription() == null ? "" : user.getDescription()) );
        model.addAttribute("user_url", (user.getPersonalWeb() == null ? "" : user.getPersonalWeb()) );
        model.addAttribute("user_email", user.getEmail());
        model.addAttribute("user_id", user.getId());

        model.addAttribute("sex_m", (user.getSex() == 'm') );
        model.addAttribute("sex_f", (user.getSex() == 'f') );
        model.addAttribute("sex_o", (user.getSex() == 'o') );
        
        return "user-settings";
    }

    @RequestMapping(value="/ajustes/guardar/personal", method=RequestMethod.POST)
    public String userSettingsSavePersonal(Model model, HttpServletRequest request, FormUserPersonal r) {
    	Message message = r.validation();
    	if(message.getCode() != 0) {
    		model.addAttribute("message", message);
    		return userSettings(model, request);
    	}
    	
    	User userLogged = userComponent.getLoggedUser();
        
    	userLogged.setName( r.getName() );
    	userLogged.setLastName( r.getLastname() );
    	userLogged.setAlias( r.getAlias() );
    	userLogged.setEmail( r.getEmail() );
    	userLogged.setSex( r.getSex() );
    	userLogged.setPhoneNumber( r.getPhone() );
    	userLogged.setCity( r.getCity() );
    	userLogged.setCountry( r.getCountry() );
    	userLogged.setDescription( r.getDescription() );
    	userLogged.setPersonalWeb( r.getUrl() );
    	
    	userRepository.save(userLogged);
    	
    	message.setCode(0);
    	message.setMessage("Los cambios han sido guardados correctamente");
    	message.setType("success");
    	model.addAttribute("message", message);

		return userSettings(model, request);
    }

    @RequestMapping("/ajustes/guardar/contrasena")
    public String userSettingsSavePass(Model model, HttpServletRequest request, FormUserPass r) {
    	Message message = r.validation();
    	if(message.getCode() != 0) {
    		model.addAttribute("message", message);
    		return userSettings(model, request);
    	}
    	
        User oldUser = userComponent.getLoggedUser();

        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        if(!bcrypt.matches( r.getPass_now(), oldUser.getPasswordHash())){
        	message.setCode(1);
        	message.setMessage("La contraseña actual no coincide con la que tiene en estos momentos");
        	message.setType("danger");
        	model.addAttribute("message", message);
    		return userSettings(model, request);
        }
        
        // Guardar contraseña
        

    	message.setCode(0);
    	message.setMessage("La nueva contraseña ha sido guardada correctamente");
    	message.setType("success");
    	model.addAttribute("message", message);
		return userSettings(model, request);
    }

    @RequestMapping("/ajustes/guardar3")
    public String userSettingsSave3(@RequestParam(value="pass_new", required=false) String newPass,
                                    @RequestParam(value="pass_new2", required=false) String newPass2,
                                    @RequestParam("pass_now") String currentPass) {
        boolean valid=true;
        return "redirect:/ajustes";
    }

    @RequestMapping(value = "/register", method = POST)
    public String register(User user){
        user.getRoles().add("ROLE_USER");
        user.getRoles().add("ROLE_EDITOR");
        userRepository.save(user);
        userComponent.setLoggedUser(user);

	    return "user_sign";
    }
    
    @RequestMapping(value = "/registerend", method = POST)
    public String registerEnd(Favourite favourite, Model model, HttpServletRequest request){
        userComponent.getLoggedUser().setFavourites(favourite);
        userRepository.save(userComponent.getLoggedUser());

        return "redirect:/portada";
    }
}
