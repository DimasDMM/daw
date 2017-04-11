package com.daw.themadridnews.categories;

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
public class CategoryService {

	public static interface CategoryBasic extends Article.Basic, User.Basic, Config.Responses {}
	public static interface Favourites extends CategoryFavourites.Basic, Article.Basic, User.Basic, Config.Responses {}
	
	@Autowired
	protected ArticleRepository articleRepository;
	
	@Autowired
	protected CommentRepository commentRepository;


	// Devuelve una pagina de articulos para una categoria determinada
	// Recordar que las paginas empiezan en 0
	public Page<Article> getArticles(String categoryId, int page, int number) {
		Page<Article> p = articleRepository.findByCategory(categoryId, new PageRequest(page, number));
		return p;
	}
}
