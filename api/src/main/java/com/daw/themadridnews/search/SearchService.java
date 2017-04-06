package com.daw.themadridnews.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.daw.themadridnews.article.Article;
import com.daw.themadridnews.article.ArticleRepository;
import com.daw.themadridnews.comment.CommentRepository;
import com.daw.themadridnews.user.User;
import com.daw.themadridnews.webconfig.Config;

@Service
public class SearchService {
	
	public static interface SearchBasic extends Article.Basic, User.Basic, Config.Responses {}
	
	public static final int N_RESULTS = 10;
	
	@Autowired
	protected ArticleRepository articleRepository;
	
	@Autowired
	protected CommentRepository commentRepository;
	

	// Devuelve una pagina de articulos para una categoria determinada
	// Recordar que las paginas empiezan en 0
	public Page<Article> getArticlesContaining(String item, int nPage) {
		Page<Article> p = articleRepository.findByTitleContaining(item, new PageRequest(nPage, N_RESULTS));
		return p;
	}
}
