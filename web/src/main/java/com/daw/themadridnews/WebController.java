package com.daw.themadridnews;

import com.daw.themadridnews.article.ArticleRepository;
import com.daw.themadridnews.article.ArticleView;
import com.daw.themadridnews.comment.CommentRepository;
import com.daw.themadridnews.user.UserComponent;
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
    private UserComponent userComponent;
    
    @Autowired
    private Config config;
    
    @Autowired
    private ArticleRepository articleRepository;
    
    @Autowired
    private CommentRepository commentRepository;
    

    @RequestMapping(value= {"/","/portada"})
    public String index(Model model, HttpServletRequest request){
        userComponent.checkRolesAndName(model, request);
        
        List<ArticleView> carrousel = ArticleView.castList( articleRepository.findFirstEachCategory() );
        model.addAttribute("carrousel", carrousel);

		model.addAttribute("page_header_date", config.getHeaderDate());
		model.addAttribute("page_header_menu", config.getMenuList());

		List<ArticleView> footerLastArticles = ArticleView.castList( articleRepository.findFirst4ByVisible(true), commentRepository );
		model.addAttribute("page_footer_last_articles", footerLastArticles);
		model.addAttribute("page_header_date", config.getHeaderDate());
		model.addAttribute("page_header_menu", config.getMenuList());
		
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
        userComponent.checkRolesAndName(model, request);

		model.addAttribute("page_header_date", config.getHeaderDate());
		model.addAttribute("page_header_menu", config.getMenuList());

		List<ArticleView> footerLastArticles = ArticleView.castList( articleRepository.findFirst4ByVisible(true), commentRepository );
		model.addAttribute("page_footer_last_articles", footerLastArticles);
		model.addAttribute("page_header_date", config.getHeaderDate());
		model.addAttribute("page_header_menu", config.getMenuList());
		
        return "privacy";
    }

    @RequestMapping(value= {"/terminos-de-uso"})
    public String termsAndConditions(Model model, HttpServletRequest request){
        userComponent.checkRolesAndName(model, request);

		model.addAttribute("page_header_date", config.getHeaderDate());
		model.addAttribute("page_header_menu", config.getMenuList());

		List<ArticleView> footerLastArticles = ArticleView.castList( articleRepository.findFirst4ByVisible(true), commentRepository );
		model.addAttribute("page_footer_last_articles", footerLastArticles);
		model.addAttribute("page_header_date", config.getHeaderDate());
		model.addAttribute("page_header_menu", config.getMenuList());
		
        return "terms_and_conditions";
    }
    
}
