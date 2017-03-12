package com.daw.themadridnews.categories;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daw.themadridnews.Config;
import com.daw.themadridnews.article.*;
import com.daw.themadridnews.comment.CommentRepository;
import com.daw.themadridnews.comment.CommentView;


@Controller
public class CategoryController {
	
	@Autowired
	protected ArticleRepository articleRepository;
	
	@Autowired
	protected CommentRepository commentRepository;
	
	@Autowired
	protected Config config;
	
	
	@RequestMapping("/categoria/{cat}")
	public String categories(Model model, @PathVariable String cat, HttpServletRequest request){
		Page<Article> articles = articleRepository.findByCategory(cat, new PageRequest(0,10));
		
		List<ArticleView> lastArticles = ArticleView.castList( articleRepository.findFirst5ByVisible(true), commentRepository );
		List<CategoryView> categories = CategoryView.castList( CategoryService.getCategoryList() );
		List<CommentView> lastComments = CommentView.castList( commentRepository.findFirst5ByOrderByDateInsertDesc() );
		List<ArticleView> otherArticles = ArticleView.castList( articleRepository.findRandom4() );
		
		model.addAttribute("categ",CategoryService.getName(cat));
		model.addAttribute("cat",cat);
		
		model.addAttribute("articulos",ArticleView.castList(articles.getContent()));
		
		model.addAttribute("showNext",!articles.isLast());
		model.addAttribute("showPrev",!articles.isFirst());
		model.addAttribute("nextPage",articles.getNumber()+1);
		model.addAttribute("prevPage",articles.getNumber()-1);
		model.addAttribute("currentPage",articles.getNumber()+1);
		model.addAttribute("totalPages",articles.getTotalPages());
		
		model.addAttribute("categories", categories);
		model.addAttribute("last_comments", lastComments);
		model.addAttribute("last_articles", lastArticles);
		model.addAttribute("other_articles", otherArticles);

		config.setPageParams(model, request);
						
		return "category";
	}
	
}
