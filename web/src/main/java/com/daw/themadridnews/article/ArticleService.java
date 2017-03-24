package com.daw.themadridnews.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.daw.themadridnews.comment.Comment;
import com.daw.themadridnews.user.User;
import com.daw.themadridnews.user.UserComponent;
import com.daw.themadridnews.webconfig.Config;

@Service
public class ArticleService {

	public static interface View extends Article.Basic, User.Basic, Config.Responses {}
	public static interface Comments extends Article.Comments, Comment.Basic, Config.Responses {}

	@Autowired
	protected ArticleRepository articleRepository;
	
	@Autowired
	protected UserComponent userComponent;
	
	protected static final int ITEMS_LIST = 20; // Numero de articulos por pagina
	
	
	// Añadir visualizacion al contador
	public Article addView(Article a) {
		if(a == null) return null;
		
		a.addView();
		return articleRepository.save(a);
	}

	// Obtener datos de un articulo
	public Article get(long id) {
		return articleRepository.findOne(id);
	}

	// Guardar cambios
	public Article save(Article a) {
		return articleRepository.save(a);
	}
	
	// Comprobar si tiene permisos de edicion
	public boolean hasPermission(User user, Article a) {
		return (user.equals( a.getAuthor() ) || user.hasRole("ROLE_ADMIN"));
	}

	// Elimiar articulo
	public void delete(Article a) {
		articleRepository.delete(a);
	}
	
	// Lista de articulos
	public Page<Article> listWhenPermission(int nPage) {
		User user = userComponent.getLoggedUser();
		Page<Article> page;
		
		if(user.hasRole("ROLE_ADMIN")) {
			page = articleRepository.findAll( new PageRequest(nPage, ITEMS_LIST, Sort.Direction.DESC, "id") );
		} else {
			User userLogged = userComponent.getLoggedUser();
			page = articleRepository.findByAuthor( userLogged, new PageRequest(nPage, ITEMS_LIST, Sort.Direction.DESC, "id") );
		}
		
		return page;
	}

	public List<Article> findFirst4() {
		return articleRepository.findFirst4ByVisible(true);
	}

	public List<Article> findFirst5() {
		return articleRepository.findFirst5ByVisible(true);
	}

	public List<Article> findFirst6() {
		return articleRepository.findFirst6ByVisible(true);
	}

	public List<Article> findRandom4() {
		return articleRepository.findRandom4();
	}
}
