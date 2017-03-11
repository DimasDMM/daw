package com.daw.themadridnews.article;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

	public List<Article> findFirst5ByVisible(boolean visible);
	
	public Page<Article> findLast10ByCategory(String category, Pageable page);

}
