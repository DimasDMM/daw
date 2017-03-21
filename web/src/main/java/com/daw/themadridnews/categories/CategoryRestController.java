package com.daw.themadridnews.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.daw.themadridnews.categories.CategoryService.PageArticlesView;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api")
public class CategoryRestController {
	
	@Autowired
	protected CategoryService categoryService;


	@JsonView(CategoryService.CategoryBasic.class) 
	@RequestMapping("/categoria/{categoryId}")
	public ResponseEntity<PageArticlesView> categories(@PathVariable String categoryId, @RequestParam(required=false) Integer page) {
		int n = 0;
		if(page != null)
			n = page.intValue() - 1;
		
		PageArticlesView pav = categoryService.getPageArticlesView(categoryId, n);
		return new ResponseEntity<>(pav, HttpStatus.OK);
	}
}
