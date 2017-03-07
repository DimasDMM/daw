package com.daw.themadridnews;

import com.daw.themadridnews.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class WebController {

    @Autowired
    private UserRepository userRepository;
    
    @RequestMapping(value= {"/","/index"})
    public String index(Model model, HttpServletRequest request){
        if(request.isUserInRole("USER"))
            model.addAttribute("user1", request.isUserInRole("USER"));
        if(request.isUserInRole("EDITOR"))
            model.addAttribute("editor", request.isUserInRole("EDITOR"));
        if(request.isUserInRole("ADVERTISING"))
            model.addAttribute("advert", request.isUserInRole("ADVERTISING"));
        return "index";
    }

    @RequestMapping(value= {"/logout"})
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "index";
    }

    @RequestMapping(value="/login")
    public String login(Model model, HttpServletRequest request){
        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        model.addAttribute("editor", request.isUserInRole("EDITOR"));
        model.addAttribute("user1", request.isUserInRole("USER"));
        return "index";
    }

    @RequestMapping(value= {"/spain"})
    public String spain(Model model, HttpServletRequest request){
        if(request.isUserInRole("USER"))
            model.addAttribute("user1", request.isUserInRole("USER"));
        return "spain";
    }
    
}
