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
		Iterator<Category> it_cat = categoryList.iterator();
		
		List<CategoryItem> list = new ArrayList<CategoryItem>();
		
		while(it_cat.hasNext()) {
			CategoryItem item = new CategoryItem();
			item.category = it_cat.next();
			List<ArticleView> l = ArticleView.castList( articleRepository.findFirst9ByCategoryAndVisible( item.category.getId(), true), commentRepository );
			
			Iterator<ArticleView> it_art = l.iterator();
			int i = 0;

			item.articles_col1 = new ArrayList<ArticleView>();
			item.articles_col2 = new ArrayList<ArticleView>();
			item.articles_col3 = new ArrayList<ArticleView>();
			
			while(it_art.hasNext()) {
				if(i < 3) {
					item.articles_col1.add(it_art.next());
				} else if(i < 6) {
					item.articles_col2.add(it_art.next());
				} else {
					item.articles_col3.add(it_art.next());
				}
				
				i++;
			}
			
			list.add(item);
		}
		
		return list;
	}
	
	
	public class CategoryItem {
		public Category category;
		public List<ArticleView> articles_col1;
		public List<ArticleView> articles_col2;
		public List<ArticleView> articles_col3;
	}
}