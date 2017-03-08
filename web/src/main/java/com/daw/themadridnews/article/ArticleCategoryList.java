package com.daw.themadridnews.article;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:config.properties")
public class ArticleCategoryList {

    @Value("#{'${article.categories.id}'.split(',')}") 
    private List<String> articleCategoriesId;

    @Value("#{'${article.categories.name}'.split(',')}") 
    private List<String> articleCategoriesName;

    private List<ArticleCategoryItem> articleCategories;

    
    public String getCategoryStr(String id) {
    	// Inicializacion de la lista de categorias si es null
    	if(articleCategories == null)
    		init();
    	
		Iterator<ArticleCategoryItem> it = articleCategories.iterator();
		
		while(it.hasNext()) {
			ArticleCategoryItem item = it.next();

			if(item.id.equals(id))
				return item.name;
		}
		
		return "Desconocido";
    }
    
    public List<ArticleCategoryItem> getCategories() {
    	// Inicializacion de la lista de categorias si es null
    	if(articleCategories == null)
    		init();
    	
    	return articleCategories;
    }
    
    // Devuelve lista de categorias con una marcada como activa
    public List<ArticleCategoryItem> getCategories(String idActive) {
    	// Inicializacion de la lista de categorias si es null
    	if(articleCategories == null)
    		init();
    	
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
    
    private void init() {
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
}