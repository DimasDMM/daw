package com.daw.themadridnews.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw.themadridnews.article.Article;
import com.daw.themadridnews.article.CategoryCommons;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api")
public class CategoryRestController {
	
	@Autowired
	protected CategoryService categoryService;
	
	public static final int N_RESULTS = 10;


	@JsonView(CategoryService.CategoryBasic.class) 
	@RequestMapping("/categoria/{categoryId}")
	public ResponseEntity<Object> categories(@PathVariable String categoryId, @RequestParam(required=false) Integer page, @RequestParam(required=false) Integer number) {
		if(!CategoryCommons.existsCategory(categoryId))
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		number =  (number != null) ? number.intValue() : N_RESULTS;
		page =  (page != null) ? page.intValue() - 1 : 0;
		
		Page<Article> p = categoryService.getArticles(categoryId, page, number);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}
}
