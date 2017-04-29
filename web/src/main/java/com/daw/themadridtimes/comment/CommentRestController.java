package com.daw.themadridtimes.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw.themadridtimes.article.ArticleService;
import com.daw.themadridtimes.comment.Comment;
import com.daw.themadridtimes.comment.CommentService;
import com.daw.themadridtimes.user.UserService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api")
public class CommentRestController {
	
	@Autowired protected ArticleService articleService;
	@Autowired protected CommentService commentService;
	@Autowired protected UserService userService;
	
	public static final int N_RESULTS = 10;
	
	
	/**
	 * Devuelve los ultimos comentarios publicados
	 */
	@RequestMapping(value="/comentarios", method=RequestMethod.GET)
	@JsonView(CommentService.ArticleView.class)
	public ResponseEntity<Object> getLastComments(@RequestParam(required=false) Integer number) {
		number =  (number != null) ? number.intValue() : N_RESULTS;
		List<Comment> l = commentService.getLastComments(number);
		return new ResponseEntity<>(l, HttpStatus.OK);
	}
}
