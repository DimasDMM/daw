package com.daw.themadridnews.search;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daw.themadridnews.article.Article;
import com.daw.themadridnews.article.ArticleRepository;
import com.daw.themadridnews.article.ArticleView;
import com.daw.themadridnews.categories.CategoryRestController.ArticleList;
import com.daw.themadridnews.comment.CommentRepository;

public class SearchRestController {
	
	@Autowired
	protected CommentRepository commentRepository;
	
	@Autowired
	protected ArticleRepository articleRepository;

	@RequestMapping("/buscar/{npage}")
	public ArticleList search(Model model, @RequestParam String searchItem, @PathVariable int npage, HttpServletRequest request){
		Page<Article> a = articleRepository.findByTitle(searchItem, new PageRequest(npage,10));
		List<ArticleView> av = ArticleView.castList( a.getContent(), commentRepository);
		
		ArticleList articleList = new ArticleList();
		articleList.content = av;
		articleList.isLast = a.isLast();
		
		return articleList;
	}
	
	
	/****/
	
	public class ArticleList {
		public List<ArticleView> content;
		public boolean isLast;
	}

}
