package com.daw.themadridnews.article;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

	public List<Article> findFirst5ByVisible(boolean visible);

}
