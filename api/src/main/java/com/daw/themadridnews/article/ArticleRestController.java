package com.daw.themadridnews.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw.themadridnews.comment.Comment;
import com.daw.themadridnews.comment.CommentService;
import com.daw.themadridnews.favourite.Favourite;
import com.daw.themadridnews.requests.ApiComment;
import com.daw.themadridnews.user.User;
import com.daw.themadridnews.user.UserService;
import com.daw.themadridnews.utils.Message;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api")
public class ArticleRestController {
	
	@Autowired protected ArticleService articleService;
	@Autowired protected CommentService commentService;
	@Autowired protected UserService userService;
	
	public static final int N_RESULTS = 10;
	
	
	/**
	 * Devuelve los ultimos "number" articulos publicados
	 */
	@JsonView(ArticleService.View.class) 
	@RequestMapping("/articulos")
	public ResponseEntity<Object> lastArticles(@RequestParam(required=false) Integer number) {
		number =  (number != null) ? number.intValue() : N_RESULTS;
		
		List<Article> l = articleService.findFirstNumber(number, true);
		return new ResponseEntity<>(l, HttpStatus.OK);
	}
	
	/**
	 * Articulos mas leidos esta semana
	 */
	@JsonView(ArticleService.Popular.class) 
	@RequestMapping("/articulos/popular")
	public ResponseEntity<Object> popularLastWeek() {
		List<Article> l = articleService.find2PopularLastWeek(true);
		return new ResponseEntity<>(l, HttpStatus.OK);
	}
	
	/**
	 * Articulos al azar de esta semana
	 */
	@JsonView(ArticleService.Popular.class) 
	@RequestMapping("/articulos/semana")
	public ResponseEntity<Object> randomLastWeek() {
		List<Article> l = articleService.findRandom4ThisWeek(true);
		return new ResponseEntity<>(l, HttpStatus.OK);
	}
	
	/**
	 * Devuelve varios articulos al azar
	 */
	@JsonView(ArticleService.Popular.class) 
	@RequestMapping("/articulos/aleatorio")
	public ResponseEntity<Object> randomArticles(@RequestParam(required=false) Integer number) {
		number =  (number != null) ? number.intValue() : N_RESULTS;
		
		List<Article> l = articleService.findRandom(number, true);
		return new ResponseEntity<>(l, HttpStatus.OK);
	}
	
	/**
	 * Devuelve favoritos
	 */
	@JsonView(ArticleService.ArticlesFavs.class) 
	@RequestMapping("/articulos/favoritos")
	public ResponseEntity<Object> favourites() {
		String category = null;
    	User userLogged = userService.getLoggedUser();
        if(userLogged != null) {
            Favourite fav = userLogged.getFavourites();
            
            if(fav != null)
            	category = fav.getRandom();
        }
        
        CategoryFavourites r = getLastArticlesByFavourite(category);
		return new ResponseEntity<>(r, HttpStatus.OK);
	}
	
	/**
	 * Devuelve un "pageable" de la categoria seleccionada
	 */
	@JsonView(ArticleService.View.class) 
	@RequestMapping("/articulos/{categoryId}")
	public ResponseEntity<Object> categories(@PathVariable String categoryId, @RequestParam(required=false) Integer page, @RequestParam(required=false) Integer number) {
		if(!CategoryCommons.existsCategory(categoryId))
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		number =  (number != null) ? number.intValue() : N_RESULTS;
		page =  (page != null) ? page.intValue() - 1 : 0;
		
		Page<Article> p = articleService.getArticlesByCategory(categoryId, page, number);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}

	/**
	 * Devuelve el articulo y los datos basicos de su autor
	 */
	@RequestMapping(value="/articulo/{id}", method=RequestMethod.GET)
	@JsonView(ArticleService.View.class)
	public ResponseEntity<Object> get(@PathVariable long id) {
		Article a = articleService.get(id, true);
		
		if(a == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(a, HttpStatus.OK);
	}
	
	/**
	 * Devuelve los comentarios de un articulo
	 */
	@RequestMapping(value="/articulo/{id}/comentarios", method=RequestMethod.GET)
	@JsonView(CommentService.Basic.class)
	public ResponseEntity<Object> getComments(@PathVariable long id, @RequestParam(required=false) Integer page) {
		if(page == null || page < 1)
			page = 1;
		
		Article a = articleService.get(id, false);
		if(a == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		Page<Comment> p = commentService.getByArticle(a, page-1);

		return new ResponseEntity<>(p, HttpStatus.OK);
	}

	/**
	 * Añade un comentario al articulo
	 */
	@RequestMapping(value="/articulo/{id}/comentarios", method=RequestMethod.POST)
	@JsonView(CommentService.Basic.class)
	public ResponseEntity<Object> sendComment(@PathVariable long id, @RequestBody ApiComment r) {
		Message message = r.validation();
		if(message.getCode() != 0)
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		
		Article article = articleService.get(id, false);
		if(article == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		// Crear objeto comentario
		User user = userService.getLoggedUser();
        long number = commentService.countByArticle(article) + 1;
        Comment comment = new Comment(article, user, number, r.getComment());
		
        // Guardar
		comment = commentService.save(comment);
		
		return new ResponseEntity<>(comment, HttpStatus.OK);
	}

	/**
	 * Añade una visualizacion al articulo
	 */
	@JsonView(ArticleService.View.class)
	@RequestMapping(value="/articulo/{id}/visualizacion", method=RequestMethod.GET)
	public ResponseEntity<Object> addView(@PathVariable long id) {
		Article a = articleService.get(id, false);
		
		if(a == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		a = articleService.addView(a);

		return new ResponseEntity<>(a, HttpStatus.OK);
	}
	
	/**
	 * Articulos para el carrousel
	 */
	@JsonView(ArticleService.View.class)
	@RequestMapping(value="/articulos/carrousel", method=RequestMethod.GET)
    public ResponseEntity<Object> carrousel() {
        List<Article> carrousel = articleService.findFirstEachCategory(false);
        return new ResponseEntity<>(carrousel, HttpStatus.OK);
	}
	
	/**
	 * FUNCIONES AUXILIARES
	 */
    private CategoryFavourites getLastArticlesByFavourite(String category) {
    	CategoryFavourites ca = new CategoryFavourites();

    	if(category == null) {
        	ca.id = null;
    		ca.name = "Todos";
            ca.articles = articleService.findFirstNumber(6, true);
        } else {
        	ca.id = category;
        	ca.name = "Categoria: "+ CategoryCommons.getName(category);
        	ca.articles = articleService.findFirst6ByCategory(category, true);
        }
    	
    	if( (ca.articles == null || ca.articles.size() == 0) && category != null )
    		ca = getLastArticlesByFavourite(null);
    	
		return ca;
	}
}
