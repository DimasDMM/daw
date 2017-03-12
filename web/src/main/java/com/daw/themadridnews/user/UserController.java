package com.daw.themadridnews.user;

import com.daw.themadridnews.Config;
import com.daw.themadridnews.FileUploadController;
import com.daw.themadridnews.favourite.Favourite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @RequestMapping("/ajustes")
    public String userSettings(Model model, HttpServletRequest request) {
        userComponent.checkRolesAndName(model, request);
        User user =userComponent.getLoggedUser();
        model.addAttribute("user_name", user.getName());
        model.addAttribute("user_lastName", user.getLastName());
        model.addAttribute("user_alias", user.getAlias());
        model.addAttribute("user_alias", user.getAlias());
        model.addAttribute("user_city", user.getCity());
        model.addAttribute("user_phone", user.getPhoneNumber());
        model.addAttribute("user_description", user.getDescription());
        model.addAttribute("user_url", user.getPersonalWeb());
        model.addAttribute("user_email", user.getEmail());
        model.addAttribute("id", user.getId());
        return "user-settings";
    }

    @RequestMapping("/ajustes/guardar1")
    public String userSettingsSave(User newUser, Model model, HttpServletRequest request) {
        boolean valid=true;
        User oldUser =userComponent.getLoggedUser();
        if(newUser.getName()==null){
            valid=false;
            model.addAttribute("name_empty", "Por favor, introduzca un nombre válido");
        }else{
            oldUser.setName(newUser.getName());
        }
        if(newUser.getLastName()==null){
            valid=false;
            model.addAttribute("lastName_empty", "Por favor, introduzca un apellido válido");
        }else{
            oldUser.setLastName(newUser.getLastName());
        }
        oldUser.setSex(newUser.getSex());
        oldUser.setCity(newUser.getCity());
        oldUser.setCountry(newUser.getCountry());
        oldUser.setDescription(newUser.getDescription());
        oldUser.setPersonalWeb(newUser.getPersonalWeb());
        if (userRepository.findByAlias(newUser.getAlias())==null) {
            valid=false;
            model.addAttribute("alias_repeated", "El alias ya está en uso");
        }
        else{
            oldUser.setAlias(newUser.getAlias());
        }
        oldUser.setPhoneNumber(newUser.getPhoneNumber());
        userRepository.save(oldUser);
        return "user-settings";
        //return userSettings(model, request);
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
