package com.daw.themadridnews.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.themadridnews.article.ArticleRepository;
import com.daw.themadridnews.categories.CategoryService.PageArticlesView;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class CategoryRestController {
	
	@Autowired
	protected CategoryService categoryService;
	
	@Autowired
	protected ArticleRepository articleRepository;


	@JsonView(CategoryService.CategoryBasic.class) 
	@RequestMapping("/categoria/{categoryId}/{nPage}")
	public ResponseEntity<PageArticlesView> categories(@PathVariable String categoryId, @PathVariable int nPage) {
		PageArticlesView pav = categoryService.getPageArticlesView(categoryId, nPage);
		return new ResponseEntity<>(pav, HttpStatus.OK);
	}
}
