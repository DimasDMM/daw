package com.daw.themadridnews.article;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArticleRepository extends JpaRepository<Article, Long> {

	public List<Article> findFirst6ByVisible(boolean visible);
	public List<Article> findFirst5ByVisible(boolean visible);
	public List<Article> findFirst4ByVisible(boolean visible);
	public List<Article> findFirst6ByCategoryAndVisible(String category, boolean visible);
	public List<Article> findFirst9ByCategoryAndVisible(String category, boolean visible);
	

	public Page<Article> findByCategory(String category, Pageable page);
	
	@Query(nativeQuery=true, value="SELECT * FROM articles ORDER BY RAND() LIMIT 4")
	public List<Article> findRandom4();

	@Query(nativeQuery=true, value="SELECT m1.* FROM daw.articles m1 LEFT JOIN daw.articles m2 ON (m1.category = m2.category AND m1.id < m2.id) WHERE m2.id IS NULL;")
	public List<Article> findFirstEachCategory();
	
}
