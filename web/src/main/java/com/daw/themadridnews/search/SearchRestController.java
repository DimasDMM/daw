package com.daw.themadridnews.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.daw.themadridnews.search.SearchService.PageArticlesView;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api")
public class SearchRestController {
	
	@Autowired
	protected SearchService searchService;


	@JsonView(SearchService.SearchBasic.class) 
	@RequestMapping(value="/buscar", method=RequestMethod.GET)
	public ResponseEntity<PageArticlesView> search(Model model, @RequestParam String item, @RequestParam(required=false) Integer page) {
		int n = 0;
		if(page != null)
			n = page.intValue() - 1;
		
		PageArticlesView pav = searchService.getPageArticlesViewContaining(item, n);
		return new ResponseEntity<>(pav, HttpStatus.OK);
	}
}
