package com.daw.themadridnews;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author URJC
 */
@Controller
public class PageController {
    
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    
}
