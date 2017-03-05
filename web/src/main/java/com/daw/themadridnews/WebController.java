package com.daw.themadridnews;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebController {
    
    @RequestMapping(value= {"/","/index"})
    public String index(){
        return "index";
    }

    @RequestMapping(value="/login")
    public String login(Model model, HttpServletRequest request){
        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        model.addAttribute("editor", request.isUserInRole("EDITOR"));
        model.addAttribute("user1", request.isUserInRole("USER"));
        return "index";
    }
    
}
