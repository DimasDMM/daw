package com.daw.themadridnews.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

	@Autowired
	protected ArticleRepository articleRepository;
	
	
	// Añadir visualizacion al contador
	public Article addView(Article a) {
		if(a == null) return null;
		
		a.addView();
		return articleRepository.save(a);
	}

	// Obtener datos de un articulo
	public Article get(long id) {
		return articleRepository.findOne(id);
	}

	public Article save(Article a) {
		return articleRepository.save(a);
	}
}
