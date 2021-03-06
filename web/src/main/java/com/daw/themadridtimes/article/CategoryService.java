package com.daw.themadridtimes.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.daw.themadridtimes.article.Article;
import com.daw.themadridtimes.article.ArticleRepository;
import com.daw.themadridtimes.article.ArticleView;
import com.daw.themadridtimes.comment.CommentRepository;
import com.daw.themadridtimes.user.User;
import com.daw.themadridtimes.webconfig.Config;
import com.fasterxml.jackson.annotation.JsonView;

@Service
public class CategoryService {
	
	public static interface CategoryBasic extends ArticleView.Basic, User.Basic, Config.Responses {}
	
	public static final int N_RESULTS = 10;
	
	@Autowired protected ArticleRepository articleService;
	@Autowired protected CommentRepository commentRepository;


	// Devuelve una pagina de articulos para una categoria determinada
	// Recordar que las paginas empiezan en 0
	public PageArticlesView getPageArticlesView(String categoryId, int page) {
		Page<Article> pa = articleService.findByCategoryAndVisible(categoryId, true, new PageRequest(page, N_RESULTS));
		
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
