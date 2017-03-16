package com.daw.themadridnews.search;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.daw.themadridnews.article.*;
import com.daw.themadridnews.comment.CommentRepository;
import com.daw.themadridnews.comment.CommentView;
import com.daw.themadridnews.webconfig.Config;


@Controller
public class SearchController {
	
	@Autowired
	protected ArticleRepository articleRepository;
	
	@Autowired
	protected CommentRepository commentRepository;
	
	@Autowired
	protected Config config;
	
	
	@RequestMapping("/buscar")
	public ModelAndView categories(Model model, @RequestParam String searchItem){
		if(searchItem == null)
			return new ModelAndView( new RedirectView("/portada") );
		
		Page<Article> articles = articleRepository.findByTitleContaining(searchItem, new PageRequest(0,10));
		
		List<ArticleView> lastArticles = ArticleView.castList( articleRepository.findFirst5ByVisible(true), commentRepository );
		List<CategoryView> categories = CategoryView.castList( CategoryCommons.getCategoryList() );
		List<CommentView> lastComments = CommentView.castList( commentRepository.findFirst5ByOrderByDateInsertDesc() );
		List<ArticleView> otherArticles = ArticleView.castList( articleRepository.findRandom4() );
		
		model.addAttribute("searchItem",searchItem);
		
		model.addAttribute("articulos",ArticleView.castList(articles.getContent()));
		
		/*model.addAttribute("showNext",!articles.isLast());
		model.addAttribute("showPrev",!articles.isFirst());
		model.addAttribute("nextPage",articles.getNumber()+1);
		model.addAttribute("prevPage",articles.getNumber()-1);
		model.addAttribute("currentPage",articles.getNumber()+1);
		model.addAttribute("totalPages",articles.getTotalPages());*/
		
		model.addAttribute("categories", categories);
		model.addAttribute("last_comments", lastComments);
		model.addAttribute("last_articles", lastArticles);
		model.addAttribute("other_articles", otherArticles);

		return new ModelAndView("search");
	}
	
}
