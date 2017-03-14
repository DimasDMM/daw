package com.daw.themadridnews.article;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.daw.themadridnews.user.User;

public interface ArticleRepository extends JpaRepository<Article, Long> {

	public List<Article> findFirst6ByVisible(boolean visible);
	public List<Article> findFirst5ByVisible(boolean visible);
	public List<Article> findFirst4ByVisible(boolean visible);
	public List<Article> findFirst6ByCategoryAndVisible(String category, boolean visible);
	public List<Article> findFirst9ByCategoryAndVisible(String category, boolean visible);
	
	public Page<Article> findByAuthor(User author, Pageable page);
	public Page<Article> findByCategory(String category, Pageable page);
	
	//@Query(nativeQuery=true, value="SELECT * FROM Article WHERE title LIKE '%title%' LIMIT 10")
	//public Page<Article> findByTitle(@Param("text") String title, Pageable page);
	
//	@Query(value = "SELECT * FROM articles WHERE title LIKE %?1% ")
//	public Page<Article> findByTitle(String text, Pageable pageable);
	
	public Page<Article> findByTitleContaining(String title, Pageable pageable);
	
	@Query(nativeQuery=true, value="SELECT * FROM articles ORDER BY RAND() LIMIT 4")
	public List<Article> findRandom4();

	@Query(nativeQuery=true, value="SELECT m1.* FROM articles m1 LEFT JOIN articles m2 ON (m1.category = m2.category AND m1.id < m2.id) WHERE m2.id IS NULL;")
	public List<Article> findFirstEachCategory();
	
	@Query(nativeQuery=true, value="SELECT * FROM articles WHERE date_insert > DATE_SUB( NOW(), INTERVAL 1 WEEK ) AND visible = 1 ORDER BY views DESC LIMIT 2")
	public List<Article> find2PopularByVisible();
	
	@Query(nativeQuery=true, value="SELECT * FROM articles WHERE date_insert > DATE_SUB( NOW(), INTERVAL 1 WEEK ) AND visible = 1 ORDER BY RAND() LIMIT 4")
	public List<Article> findRandom4ThisWeek();
	
}
