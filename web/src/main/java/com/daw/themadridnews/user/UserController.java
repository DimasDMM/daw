package com.daw.themadridnews.user;

import com.daw.themadridnews.Config;
import com.daw.themadridnews.favourite.Favourite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
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
    @RequestMapping("/user-settings")
    public String userSettings(Model model, HttpServletRequest request) {
		User user = userComponent.getLoggedUser();
		model.addAttribute("user_name", user.getName());
		model.addAttribute("user_lastName", user.getLastName());
		model.addAttribute("user_alias", user.getAlias());
		model.addAttribute("user_alias", user.getAlias());
		model.addAttribute("user_city", user.getCity());
		model.addAttribute("user_phone", user.getPhoneNumber());
		model.addAttribute("user_description", user.getDescription());
		model.addAttribute("user_url", user.getPersonalWeb());

		config.setPageParams(model, request);

        return "user-settings";
    }

    @RequestMapping("/#")
    public String userSettingsSave(Model model, HttpServletRequest request) {
    	config.setPageParams(model, request);
	
		return userSettings(model, request);
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
    public String registerEnd(Favourite theme, Model model, HttpServletRequest request){
        userComponent.getLoggedUser().setThemes(theme);
        userRepository.save(userComponent.getLoggedUser());

        return "redirect:/portada";
    }
}
