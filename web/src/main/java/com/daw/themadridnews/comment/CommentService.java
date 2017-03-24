package com.daw.themadridnews.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.themadridnews.article.Article;
import com.daw.themadridnews.comment.CommentRepository;

@Service
public class CommentService {

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
	
	public List<Comment> getLast5() {
		return commentRepository.findFirst5ByOrderByDateInsertDesc();
	}

	public long countByArticle(Article a) {
		return commentRepository.countByArticle(a);
	}
}
