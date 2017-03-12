package com.daw.themadridnews.user;

import com.daw.themadridnews.Config;
import com.daw.themadridnews.favourite.Favourite;
import com.daw.themadridnews.files.FileUploadController;

import com.daw.themadridnews.requests.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String userSettingsSave1(User newUser, Model model) {
        boolean valid=true;
        User oldUser =userComponent.getLoggedUser();
        if(newUser.getName()==null || !Validator.strOnlyLetters(newUser.getName())){
            valid=false;
            model.addAttribute("name_empty", "Por favor, introduzca un nombre válido");
        }else{
            oldUser.setName(newUser.getName());
        }
        if(newUser.getLastName()==null || !Validator.strOnlyLetters(newUser.getLastName())){
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
        if (userRepository.findByAlias(newUser.getAlias())!=null) {
            valid=false;
            model.addAttribute("alias_repeated", "El alias ya está en uso");
        }
        else{
            oldUser.setAlias(newUser.getAlias());
        }
        oldUser.setPhoneNumber(newUser.getPhoneNumber());
        if (valid)
            userRepository.save(oldUser);
        return "redirect:/ajustes";
    }

    @RequestMapping("/ajustes/guardar2")
    public String userSettingsSave2(Model model, @RequestParam("email") String newEmail,
                                    @RequestParam(value="pass_now", required=false) String currentPass) {
        User oldUser =userComponent.getLoggedUser();
        if(newEmail.equals(oldUser.getEmail())){
            return "redirect:/ajustes";
        }else{
            if(Validator.strValidMail(newEmail) && (new BCryptPasswordEncoder().matches(currentPass, oldUser.getPasswordHash()))){
                oldUser.setEmail(newEmail);
                userRepository.save(oldUser);
            }
            else{
                model.addAttribute("email_error","Por favor, introduzca un email válido y su contraseña");
            }
        }
        return "redirect:/ajustes";
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
