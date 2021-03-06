package com.daw.themadridtimes.comment;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.daw.themadridtimes.article.Article;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	public long countByArticle(Article article);

	public Page<Comment> findByArticle(Article article, Pageable page);

	@Query(nativeQuery=true, value="SELECT * FROM comments ORDER BY date_insert DESC LIMIT ?1")
	public List<Comment> findFirstComments(int number);

}
