package com.daw.themadridnews.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.themadridnews.article.Article;
import com.daw.themadridnews.comment.CommentRepository;
import com.daw.themadridnews.webconfig.Config;

@Service
public class CommentService {

	public static interface Basic extends Comment.Basic, Config.Responses {}
	public static interface ArticleView extends Comment.Basic, Comment.ArticleView, Article.Basic, Config.Responses {}

	@Autowired
	protected CommentRepository commentRepository;
	

	public Comment save(Comment comment) {
		if(comment == null) return null;
		return commentRepository.save(comment);
	}
	
	public Comment get(long id) {
		return commentRepository.findOne(id);
	}

	public List<Comment> get(Article a) {
		return commentRepository.findByArticle(a);
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
