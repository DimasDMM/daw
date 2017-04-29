package com.daw.themadridtimes.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.daw.themadridtimes.article.Article;
import com.daw.themadridtimes.comment.CommentRepository;
import com.daw.themadridtimes.user.User;
import com.daw.themadridtimes.webconfig.Config;

@Service
public class CommentService {

	public static interface Basic extends Comment.Basic, User.Basic, Config.Responses {}
	public static interface ArticleView extends Comment.Basic, Comment.ArticleView, Article.Basic, Config.Responses {}

	@Autowired
	protected CommentRepository commentRepository;
	
	protected static final int ITEMS_LIST = 10;
	

	public Comment save(Comment comment) {
		if(comment == null) return null;
		return commentRepository.save(comment);
	}
	
	public Comment get(long id) {
		return commentRepository.findOne(id);
	}

	// La pagina empieza en 0
	public Page<Comment> getByArticle(Article a, int nPage) {
		return commentRepository.findByArticle(a, new PageRequest(nPage, ITEMS_LIST, Sort.Direction.DESC, "id"));
	}

	public long countByArticle(Article a) {
		return commentRepository.countByArticle(a);
	}

	/**
	 * Obtener ultimos X comentarios
	 */
	public List<Comment> getLastComments(int number) {
		return commentRepository.findFirstComments(number);
	}
}
