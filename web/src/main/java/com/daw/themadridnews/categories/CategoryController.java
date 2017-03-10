package com.daw.themadridnews.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.daw.themadridnews.article.*;


@Controller
public class CategoryController {
	/*
	@Autowired
	ArticleCategoryRepository categoryRepository;
	
	@Autowired
	ArticleRepository articleRepository;
	
	@RequestMapping("/category/{cat}")
	public String categories(Model model, @PathVariable String cat, Pageable pag){
		
		model.addAttribute("category",cat);
		
		Page<ArticleCategory> articles = categoryRepository.findAll(pag);
				
		return cat;
		
	}
	*/
}
