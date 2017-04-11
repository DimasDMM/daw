package com.daw.themadridnews.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.daw.themadridnews.comment.Comment;
import com.daw.themadridnews.comment.CommentService;
import com.daw.themadridnews.requests.ApiComment;
import com.daw.themadridnews.user.User;
import com.daw.themadridnews.user.UserService;
import com.daw.themadridnews.utils.Message;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api")
public class ArticleRestController {
	
	@Autowired
	protected ArticleService articleService;

	@Autowired
	protected CommentService commentService;
	
	@Autowired
	protected UserService userService;
	

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
	@JsonView(ArticleService.Comments.class)
	public ResponseEntity<Object> getComments(@PathVariable long id) {
		Article a = articleService.get(id, false);
		
		if(a == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(a, HttpStatus.OK);
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
	 * Añade un comentario al articulo
	 */
	@RequestMapping(value="/articulo/{id}", method=RequestMethod.POST)
	@JsonView(Comment.Basic.class)
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
}
