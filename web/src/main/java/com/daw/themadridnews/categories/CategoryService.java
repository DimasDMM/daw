package com.daw.themadridnews.categories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.daw.themadridnews.article.Article;
import com.daw.themadridnews.article.ArticleRepository;
import com.daw.themadridnews.article.ArticleView;
import com.daw.themadridnews.comment.CommentRepository;
import com.fasterxml.jackson.annotation.JsonView;

@Service
public class CategoryService {
	
	public static interface CategoryBasic extends ArticleView.Basic {}
	
	@Autowired
	protected ArticleRepository articleRepository;
	
	@Autowired
	protected CommentRepository commentRepository;


	// Devuelve una pagina de articulos para una categoria determinada
	public PageArticlesView getPageArticlesView(String categoryId, int nPage) {
		Page<Article> pa = articleRepository.findByCategory(categoryId, new PageRequest(nPage,10));
		
		PageArticlesView pav = new PageArticlesView();
		pav.isLast = pa.isLast();
		pav.content = ArticleView.castList( pa.getContent(), commentRepository );
		
		return pav;
	}
	
	
	/*************/
	
	public class PageArticlesView {
		@JsonView(CategoryService.CategoryBasic.class) public List<ArticleView> content;
		@JsonView(CategoryService.CategoryBasic.class) public boolean isLast;
	}
}
