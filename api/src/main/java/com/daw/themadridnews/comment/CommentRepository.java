package com.daw.themadridnews.comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.daw.themadridnews.article.Article;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	public long countByArticle(Article article);

	public List<Comment> findByArticle(Article article);

	@Query(nativeQuery=true, value="SELECT * FROM comments ORDER BY date_insert ASC LIMIT ?1")
	public List<Comment> findFirstComments(int number);

}
