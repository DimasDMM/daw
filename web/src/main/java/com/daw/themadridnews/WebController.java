package com.daw.themadridnews;

import com.daw.themadridnews.user.UserComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class WebController {

    @Autowired
    private UserComponent userComponent;
    

    @RequestMapping(value= {"/","/inicio"})
    public String index(Model model, HttpServletRequest request){
        userComponent.checkRolesAndName(model, request);
        return "index";
    }

    @RequestMapping(value= {"/logout"})
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "index";
    }

    @RequestMapping(value="/login")
    public String login(Model model, HttpServletRequest request){
        userComponent.checkRolesAndName(model, request);
        return "index";
    }

    @RequestMapping(value= {"/privacidad"})
    public String privacy(Model model, HttpServletRequest request){
        userComponent.checkRolesAndName(model, request);
        return "privacy";
    }

    @RequestMapping(value= {"/terminos-de-uso"})
    public String termsAndConditions(Model model, HttpServletRequest request){
        userComponent.checkRolesAndName(model, request);
        return "terms_and_conditions";
    }
    
}
