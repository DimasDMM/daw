package com.daw.themadridnews;

import com.daw.themadridnews.article.ArticleRepository;
import com.daw.themadridnews.article.ArticleView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class WebController {
    
    @Autowired
    private Config config;
    
    @Autowired
    private ArticleRepository articleRepository;
    

    @RequestMapping(value= {"/","/portada"})
    public String index(Model model, HttpServletRequest request){
        List<ArticleView> carrousel = ArticleView.castList( articleRepository.findFirstEachCategory() );
        model.addAttribute("carrousel", carrousel);

        config.setPageParams(model, request);
		
        return "index";
    }

    @RequestMapping(value= {"/logout"})
    public String logout(Model model, HttpServletRequest request) throws ServletException {
        request.logout();
		
        return index(model, request);
    }

    @RequestMapping(value="/login")
    public String login(Model model, HttpServletRequest request){
        return index(model, request);
    }

    @RequestMapping(value= {"/privacidad"})
    public String privacy(Model model, HttpServletRequest request){
    	config.setPageParams(model, request);
		
        return "privacy";
    }

    @RequestMapping(value= {"/terminos-de-uso"})
    public String termsAndConditions(Model model, HttpServletRequest request){
    	config.setPageParams(model, request);
		
        return "terms_and_conditions";
    }
    
}
