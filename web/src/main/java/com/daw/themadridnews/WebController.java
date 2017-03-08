package com.daw.themadridnews;

import com.daw.themadridnews.user.User;
import com.daw.themadridnews.user.UserComponent;
import com.daw.themadridnews.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.rmi.runtime.Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class WebController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserComponent userComponent;

    @RequestMapping(value= {"/","/index"})
    public String index(Model model, HttpServletRequest request){
        checkRolesAndName(model, request);
        return "index";
    }

    @RequestMapping(value= {"/logout"})
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "index";
    }

    @RequestMapping(value="/login")
    public String login(Model model, HttpServletRequest request){
        checkRolesAndName(model, request);
        return "index";
    }

    @RequestMapping(value= {"/spain"})
    public String spain(Model model, HttpServletRequest request){
        checkRolesAndName(model, request);
        return "spain";
    }

    //metodo auxiliar para cambiar la vista según los roles para cada pagina y mostrar el alias/nombre de usuario
    private void checkRolesAndName(Model model, HttpServletRequest request) {
        model.addAttribute("user1", request.isUserInRole("USER"));
        model.addAttribute("editor", request.isUserInRole("EDITOR"));
        model.addAttribute("advert", request.isUserInRole("ADVERTISING"));
        User user = userComponent.getLoggedUser();
        if(user!=null) {
            if(user.getAlias()!=null)
                model.addAttribute("user1", user.getAlias());
            else
                model.addAttribute("user1", user.getName());
        }
    }
    
}
