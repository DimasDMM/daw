package com.daw.themadridtimes.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.daw.themadridtimes.article.Article;
import com.fasterxml.jackson.annotation.JsonView;


@RestController
@RequestMapping("/api")
public class SearchRestController {
	
	@Autowired
	protected SearchService searchService;


	@JsonView(SearchService.SearchBasic.class) 
	@RequestMapping(value="/buscar", method=RequestMethod.GET)
	public ResponseEntity<Object> search(@RequestParam String search, @RequestParam(required=false) Integer page) {
		int n = 0;
		if(page != null)
			n = page.intValue() - 1;

		Page<Article> p = searchService.getArticlesContaining(search, n);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}
}
