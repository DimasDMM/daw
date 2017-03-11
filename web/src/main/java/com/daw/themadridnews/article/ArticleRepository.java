package com.daw.themadridnews.article;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArticleRepository extends JpaRepository<Article, Long> {

	public List<Article> findFirst5ByVisible(boolean visible);
	
	public Page<Article> findLast10ByCategory(String category, Pageable page);
	
	@Query(nativeQuery=true, value="SELECT * FROM articles ORDER BY RAND() LIMIT 4")
	public List<Article> findRandom4();

}
