package com.daw.themadridnews;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/user-settings")
    public String userSettings(){
        return "user-settings";
    }
    
}
