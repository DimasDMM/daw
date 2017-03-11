package com.daw.themadridnews;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.daw.themadridnews.article.ArticleRepository;
import com.daw.themadridnews.article.ArticleView;
import com.daw.themadridnews.article.Category;
import com.daw.themadridnews.article.CategoryService;
import com.daw.themadridnews.comment.CommentRepository;

@Configuration
@PropertySource("classpath:config.properties")
public class Config {

    @Value("${path.img.articles}") 
    private String pathImgArticles;

    @Value("${path.img.users}") 
    private String pathImgUsers;

    @Value("${path.img.ads}") 
    private String pathImgAds;
    
    @Autowired
    private ArticleRepository articleRepository;
    
    @Autowired
    private CommentRepository commentRepository;

    
	public String getPathImgArticles() {
		return pathImgArticles;
	}

	public String getPathImgUsers() {
		return pathImgUsers;
	}
	
	public String getPathImgAds() {
		return pathImgAds;
	}
	
	public List<CategoryItem> getMenuList() {
		List<Category> categoryList = CategoryService.getCategoryList();
		Iterator<Category> it = categoryList.iterator();
		
		List<CategoryItem> list = new ArrayList<CategoryItem>();
		
		while(it.hasNext()) {
			CategoryItem item = new CategoryItem();
			item.category = it.next();
			item.articles = ArticleView.castList( articleRepository.findFirst9ByCategoryAndVisible( item.category.getId(), true), commentRepository );
			list.add(item);
		}
		
		return list;
	}
	
	
	public class CategoryItem {
		public Category category;
		public List<ArticleView> articles;
	}
}