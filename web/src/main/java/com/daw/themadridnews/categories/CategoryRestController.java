package com.daw.themadridnews.categories;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.themadridnews.Config;
import com.daw.themadridnews.article.*;
import com.daw.themadridnews.comment.CommentRepository;

import com.daw.themadridnews.article.Article;

@RestController
public class CategoryRestController {
	
	@Autowired
	protected CommentRepository commentRepository;
	
	@Autowired
	protected ArticleRepository articleRepository;

	@RequestMapping("/categoria/{cat}/{npage}")
	public ArticleList categories(Model model, @PathVariable String cat, @PathVariable int npage, HttpServletRequest request){
		Page<Article> a = articleRepository.findByCategory(cat, new PageRequest(npage,10));
		List<ArticleView> av = ArticleView.castList( a.getContent(), commentRepository);
		
		ArticleList articleList = new ArticleList();
		articleList.content = av;
		articleList.isLast = a.isLast();
		
		return articleList;
	}
	
	
	/****/
	
	public class ArticleList {
		public List<ArticleView> content;
		public String contentShort;
		public boolean isLast;
	}
}
