package com.daw.themadridnews.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.daw.themadridnews.article.Article;
import com.daw.themadridnews.article.ArticleService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api")
public class HomeRestController {
	
	@Autowired
	private ArticleService articleService;

	@JsonView(ArticleService.View.class)
	@RequestMapping(value="/carrousel", method=RequestMethod.GET)
    public ResponseEntity<Object> carrousel() {
        List<Article> carrousel = articleService.findFirstEachCategory();
        return new ResponseEntity<>(carrousel, HttpStatus.OK);
	}
}
