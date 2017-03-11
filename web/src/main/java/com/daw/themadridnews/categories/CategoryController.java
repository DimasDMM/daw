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
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("/categoria/{cat}/{npage}")
	public String categories(Model model, @PathVariable String cat, Pageable pag, @PathVariable int npage){
		
		Page<Article> articles = articleRepository.findByCategory(cat, new PageRequest(npage,10));
		
		model.addAttribute("category",CategoryService.getName(cat));
		
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
