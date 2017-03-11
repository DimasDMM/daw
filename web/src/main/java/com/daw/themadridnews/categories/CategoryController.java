package com.daw.themadridnews.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daw.themadridnews.Config;
import com.daw.themadridnews.article.*;


@Controller
public class CategoryController {
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	 protected Config config;
	
	@RequestMapping("/categoria/{cat}/{npage}")
	public String categories(Model model, @PathVariable String cat, @PathVariable int npage){
		npage = ( npage <= 0 ? 0 : npage-1 );
		return showCategories(model, cat, npage);
	}
	
	@RequestMapping("/categoria/{cat}")
	public String categories(Model model, @PathVariable String cat){
		return showCategories(model, cat, 0);
	}
	
	private String showCategories(Model model, String cat, int npage) {
		Page<Article> articles = articleRepository.findByCategory(cat, new PageRequest(npage,10));
		
		model.addAttribute("categ",CategoryService.getName(cat));
		model.addAttribute("cat",cat);
		
		model.addAttribute("page_header", config.getMenuList());
		
		model.addAttribute("articulos",ArticleView.castList(articles.getContent()));
		
		model.addAttribute("showNext",!articles.isLast());
		model.addAttribute("showPrev",!articles.isFirst());
		model.addAttribute("nextPage",articles.getNumber()+1);
		model.addAttribute("prevPage",articles.getNumber()-1);
		model.addAttribute("currentPage",articles.getNumber()+1);
		model.addAttribute("totalPages",articles.getTotalPages());
						
		return "category";
	}
	
}
