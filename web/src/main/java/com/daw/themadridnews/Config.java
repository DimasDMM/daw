package com.daw.themadridnews;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
public class Config {

	@Value("#{'${article.categories.id}'.split(',')}") 
    private List<String> articleCategoriesId;

    @Value("#{'${article.categories.name}'.split(',')}") 
    private List<String> articleCategoriesName;

    
    private List<ArticleCategoryItem> articleCategories;

    
    public List<ArticleCategoryItem> getArticleCategories() {
    	// Inicializacion de la lista de categorias si es null
    	if(articleCategories == null)
    		initArticleCategories();
    	
    	return articleCategories;
    }
    
    // Devuelve lista de categorias con una marcada como activa
    public List<ArticleCategoryItem> getArticleCategories(String idActive) {
    	List<ArticleCategoryItem> articleCategories = new ArrayList<ArticleCategoryItem>();
		Iterator<String> it_id = articleCategoriesId.iterator();
		Iterator<String> it_name = articleCategoriesName.iterator();
		
		while(it_id.hasNext()) {
			ArticleCategoryItem item = new ArticleCategoryItem();
			item.id = it_id.next();
			item.name = it_name.next();

			if(item.id.equals(idActive))
				item.isActive = true;
			
			articleCategories.add( item );
		}
		
		return articleCategories;
    }
    
    private void initArticleCategories() {
    	articleCategories = new ArrayList<ArticleCategoryItem>();
		Iterator<String> it_id = articleCategoriesId.iterator();
		Iterator<String> it_name = articleCategoriesName.iterator();
		
		while(it_id.hasNext()) {
			ArticleCategoryItem item = new ArticleCategoryItem();
			item.id = it_id.next();
			item.name = it_name.next();
			
			articleCategories.add( item );
		}
    }
    
    public class ArticleCategoryItem {
    	public String id;
    	public String name;
    	public boolean isActive = false; // Atributo para usos varios: marcar un input select, pagina que se esta visualizando, etc
    }
}
