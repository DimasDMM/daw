package com.daw.themadridtimes.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.daw.themadridtimes.article.Article;
import com.daw.themadridtimes.article.ArticleView;
import com.daw.themadridtimes.comment.CommentRepository;
import com.fasterxml.jackson.annotation.JsonView;


@RestController
@RequestMapping("/api")
public class SearchRestController {
	
	@Autowired protected SearchService searchService;
	@Autowired protected CommentRepository commentRepository;
	

	@JsonView(SearchService.SearchBasic.class) 
	@RequestMapping(value="/buscar", method=RequestMethod.GET)
	public ResponseEntity<Object> search(@RequestParam String search, @RequestParam(required=false) Integer page, @RequestParam(required=false) boolean view) {
		int n = 0;
		if(page != null)
			n = page.intValue() - 1;

		Page<Article> p = searchService.getArticlesContaining(search, n);
		
		if(!view) {
			return new ResponseEntity<>(p, HttpStatus.OK);
		} else {
			PageArticleView pav = new PageArticleView();
			pav.content = ArticleView.castList( p.getContent(), commentRepository );
			pav.isLast = p.isLast();
			
			return new ResponseEntity<>(pav, HttpStatus.OK);
		}
	}
	
	public class PageArticleView {
		@JsonView(SearchService.SearchBasic.class) public List<ArticleView> content;
		@JsonView(SearchService.SearchBasic.class) public boolean isLast;
	}
}
