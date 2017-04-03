package com.daw.themadridnews.comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.daw.themadridnews.article.Article;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	public long countByArticle(Article article);

	public List<Comment> findByArticle(Article article);

	public List<Comment> findFirst5ByOrderByDateInsertDesc();

}
