package com.daw.themadridnews.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.daw.themadridnews.article.*;


@Controller
public class CategoryController {
	
	@Autowired
	ArticleRepository articleRepository;
	
	@RequestMapping("/category/{cat}")
	public String categories(Model model, @PathVariable String cat, Pageable pag){
		
		Page<Article> articles = articleRepository.findAll(pag);
		
		model.addAttribute("category",cat);
		
		model.addAttribute("articulos",ArticleView.castList(articles.getContent()));
						
		return cat;
		
	}
	
}
