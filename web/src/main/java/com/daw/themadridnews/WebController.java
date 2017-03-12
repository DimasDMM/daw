package com.daw.themadridnews;

import com.daw.themadridnews.ad.AdRepository;
import com.daw.themadridnews.ad.AdService;
import com.daw.themadridnews.ad.AdView;
import com.daw.themadridnews.article.ArticleRepository;
import com.daw.themadridnews.article.ArticleView;
import com.daw.themadridnews.article.CategoryService;
import com.daw.themadridnews.article.CategoryView;
import com.daw.themadridnews.comment.CommentRepository;
import com.daw.themadridnews.comment.CommentView;
import com.daw.themadridnews.favourite.Favourite;
import com.daw.themadridnews.user.User;
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
    private Config config;
    
    @Autowired
    private ArticleRepository articleRepository;
    
    @Autowired
    private CommentRepository commentRepository;
    
    @Autowired
    private AdRepository adRepository;
    
    @Autowired
    private UserComponent userComponent;
    

    @RequestMapping(value= {"/","/portada"})
    public String index(Model model, HttpServletRequest request){
    	User user = userComponent.getLoggedUser();
    	
    	// Seccion: Carrousel
        List<ArticleView> carrousel = ArticleView.castList( articleRepository.findFirstEachCategory() );
        model.addAttribute("carrousel", carrousel);
        
        // Seccion: Ultimas noticias favoritas
        String category = null;
        if(user != null) {
            Favourite fav = user.getFavourites();
            
            if(fav != null)
            	category = fav.getRandom();
        }
        
        List<ArticleView> lastArticlesSec = null;
        if(category == null) {
            model.addAttribute("last_articles_category", "Todos");
        	lastArticlesSec = ArticleView.castList( articleRepository.findFirst6ByVisible(true), commentRepository );
        } else {
            model.addAttribute("last_articles_category", "Categoria: "+ CategoryService.getName(category));
            lastArticlesSec = ArticleView.castList( articleRepository.findFirst6ByCategoryAndVisible(category, true), commentRepository );
        }
        
        ArticleView lastArticlesFeat = lastArticlesSec.get(0);
        lastArticlesSec.remove(0);

        model.addAttribute("last_articles_feat", lastArticlesFeat);
        model.addAttribute("last_articles_sec", lastArticlesSec);
        
        // Seccion: Ultimos comentarios
        List<CommentView> lastComments = CommentView.castList( commentRepository.findFirst5ByOrderByDateInsertDesc() );
        model.addAttribute("last_comments", lastComments);
        
        // Seccion: Categorias
		List<CategoryView> categories = CategoryView.castList( CategoryService.getCategoryList() );
        model.addAttribute("categories", categories);
        
        // Seccion: Anuncio
        AdView ad = new AdView( AdService.getRandom(adRepository) );
        model.addAttribute("ad_banner", ad);
        
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
