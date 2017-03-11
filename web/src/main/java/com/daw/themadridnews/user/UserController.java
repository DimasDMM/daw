package com.daw.themadridnews.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class UserController {
    //Attributes
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserComponent userComponent;
    
    //Requests
    @RequestMapping("/user-settings")
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

        return "user-settings";
    }

    @RequestMapping("/#")
    public String userSettingsSave(Model model, HttpServletRequest request) {
	User user =userComponent.getLoggedUser();

	return userSettings(model, request);
    }

    @RequestMapping(value = "/register", method = POST)
    public String user_sign(Model model, User user){
        user.getRoles().add("USER");
        userRepository.save(user);

	return "user_sign";
    }
}
