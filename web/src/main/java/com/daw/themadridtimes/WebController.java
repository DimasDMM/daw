package com.daw.themadridtimes;

import com.daw.themadridtimes.ad.AdService;
import com.daw.themadridtimes.ad.AdView;
import com.daw.themadridtimes.article.ArticleService;
import com.daw.themadridtimes.article.ArticleView;
import com.daw.themadridtimes.article.CategoryCommons;
import com.daw.themadridtimes.article.CategoryView;
import com.daw.themadridtimes.comment.CommentRepository;
import com.daw.themadridtimes.comment.CommentService;
import com.daw.themadridtimes.comment.CommentView;
import com.daw.themadridtimes.favourite.Favourite;
import com.daw.themadridtimes.files.FileUploadCommons;
import com.daw.themadridtimes.requests.FormSubscription;
import com.daw.themadridtimes.requests.FormSignupNew;
import com.daw.themadridtimes.requests.FormSignupPreferences;
import com.daw.themadridtimes.subscription.SubscriptionService;
import com.daw.themadridtimes.user.User;
import com.daw.themadridtimes.user.UserService;
import com.daw.themadridtimes.utils.Message;
import com.daw.themadridtimes.webconfig.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


@Controller
public class WebController {
    
    @Autowired protected Config config;
    @Autowired protected ArticleService articleService;
    @Autowired protected CommentService commentService;
    @Autowired protected CommentRepository commentRepository;
    @Autowired protected AdService adService;
    @Autowired protected UserService userService;
	@Autowired protected SubscriptionService subscriptionService;
    

    @RequestMapping(value= {"/","/portada"})
    public ModelAndView index(Model model) {
    	
    	// Seccion: Carrousel
        List<ArticleView> carrousel = ArticleView.castList( articleService.findFirstEachCategory(false) );
        model.addAttribute("carrousel", carrousel);
        
        // Seccion: Ultimas noticias favoritas
        String category = null;
    	User userLogged = userService.getLoggedUser();
        if(userLogged != null) {
            Favourite fav = userLogged.getFavourites();
            
            if(fav != null)
            	category = fav.getRandom();
        }
        
        List<ArticleView> lastArticlesSec = getLastArticlesByFavourite(model, category);
        ArticleView lastArticlesFeat = lastArticlesSec.get(0);
        lastArticlesSec.remove(0);

        model.addAttribute("last_articles_feat", lastArticlesFeat);
        model.addAttribute("last_articles_sec", lastArticlesSec);
        
        // Seccion: Ultimos articulos de todo
        List<ArticleView> lastArticles = ArticleView.castList( articleService.findFirstNumber(6, false), commentRepository );
        model.addAttribute("last_articles", lastArticles);
        
        // Seccion: Articulos mas leidos
        List<ArticleView> popularArticles = ArticleView.castList( articleService.find2PopularLastWeek(false), commentRepository );
        model.addAttribute("popular_articles", popularArticles);
        
        // Seccion: Ultimos comentarios
        List<CommentView> lastComments = CommentView.castList( commentService.getLastComments(5) );
        model.addAttribute("last_comments", lastComments);
        
        // Seccion: Noticias de la semana
        List<ArticleView> weekArticles = ArticleView.castList( articleService.findRandom4ThisWeek(false) );
        model.addAttribute("week_articles", weekArticles);
        
        // Seccion: Categorias
		List<CategoryView> categories = CategoryView.castList( CategoryCommons.getCategoryList() );
        model.addAttribute("categories", categories);
        
        // Seccion: Anuncio
        AdView ad = new AdView( adService.getRandom() );
        model.addAttribute("ad_banner", ad);
		
		return new ModelAndView("index");
    }

	@RequestMapping(value= {"/new"})
    public ModelAndView angular(Model model) {
		return new ModelAndView("new/index");
    }

	@RequestMapping(value= {"/privacidad"})
    public ModelAndView privacy(Model model) {
		return new ModelAndView("privacy");
    }

    @RequestMapping(value= {"/terminos-de-uso"})
    public ModelAndView termsAndConditions(Model model){
		return new ModelAndView("terms-and-conditions");
    }

	@RequestMapping(value="/portada/subscripcion", method=RequestMethod.POST)
	public ModelAndView subscription(Model model, FormSubscription r) {
		Message message = r.validation();
		
		if(message.getCode() == 0)
			subscriptionService.subscribe(r.getEmail());
		
		model.addAttribute("modal_subscription", true);
		model.addAttribute("modal_type", (message.getCode() == 0 ? "success" : "danger" ) );
		model.addAttribute("modal_title", (message.getCode() == 0 ? "Perfecto" : "¡Ups!" ) );
		model.addAttribute("modal_message", (message.getCode() == 0 ? "Te has subscrito correctamente a nuestro boletin. Pronto comenzarás a recibir noticias en tu correo electrónico." : message) );
		
		return index(model);
	}

    @RequestMapping(value= {"/logout"})
    public ModelAndView logout(Model model, HttpServletRequest request) throws ServletException {
        request.logout();
		
        return index(model);
    }

    @RequestMapping(value="/login")
    public ModelAndView login(Model model){
        return index(model);
    }
    
    @RequestMapping(value="/loginError")
    public ModelAndView loginError(Model model) {
		return new ModelAndView("login-error");
    }

    @RequestMapping(value="/registro", method=RequestMethod.POST)
	public ModelAndView registerNew(Model model, FormSignupNew r) {
    	Message message = r.validation();
    	if(message.getCode() != 0)
			return new ModelAndView( new RedirectView("/portada") );
    	
    	User user = new User();
    	user.setName( r.getName() );
    	user.setLastname( r.getLastname() );
    	user.setEmail( r.getEmail() );
    	user.setPasswordHash( r.getPass_new1() );
    	user.getRoles().add("ROLE_USER");
        
    	userService.save(user);
        userService.setLoggedUser(user);
        
		return new ModelAndView("signup-preferences");
    }
    
    @RequestMapping(value="/registro/ajustes-iniciales", method=RequestMethod.POST)
    public ModelAndView registerPreferences(Model model, FormSignupPreferences r, @RequestParam("file") MultipartFile file){
        User userLogged = userService.getLoggedUser();
        
        if(!file.isEmpty())
        	FileUploadCommons.saveImage( file, config.getPathImgUsers(), String.valueOf(userLogged.getId()) );
    	
    	Favourite favourite = userLogged.getFavourites();
    	favourite.setMadrid( r.get("madrid") );
    	favourite.setSpain( r.get("spain") );
    	favourite.setWorld( r.get("world") );
    	favourite.setSports( r.get("sports") );
    	favourite.setTechnology( r.get("technology") );
    	favourite.setCulture( r.get("culture") );
    	
    	userLogged.setFavourites(favourite);
    	userService.save(userLogged);
    	
    	model.addAttribute("signup_success", true);

		return new ModelAndView("signup-preferences");
    }

    /*
     * FUNCIONES AUXILIARES
     */
    
    private List<ArticleView> getLastArticlesByFavourite(Model model, String category) {
    	List<ArticleView> lastArticles;
    	
    	if(category == null) {
        	model.addAttribute("last_articles_category_name", false);
            model.addAttribute("last_articles_category_id", false);
            lastArticles = ArticleView.castList( articleService.findFirstNumber(6, false), commentRepository );
        } else {
            model.addAttribute("last_articles_category_name", "Categoria: "+ CategoryCommons.getName(category));
            model.addAttribute("last_articles_category_id", category);
            lastArticles = ArticleView.castList( articleService.findFirst6ByCategory(category, false), commentRepository );
        }
    	
    	if(lastArticles == null || lastArticles.size() == 0)
    		lastArticles = getLastArticlesByFavourite(model, null);
    	
		return lastArticles;
	}
}
