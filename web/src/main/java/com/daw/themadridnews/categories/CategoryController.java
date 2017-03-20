package com.daw.themadridnews.categories;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.daw.themadridnews.article.ArticleRepository;
import com.daw.themadridnews.article.ArticleView;
import com.daw.themadridnews.article.Category;
import com.daw.themadridnews.article.CategoryCommons;
import com.daw.themadridnews.article.CategoryView;
import com.daw.themadridnews.categories.CategoryService.PageArticlesView;
import com.daw.themadridnews.comment.CommentRepository;
import com.daw.themadridnews.comment.CommentView;


@Controller
public class CategoryController {
	
	@Autowired
	protected CategoryService categoryService;
	
	@Autowired
	protected ArticleRepository articleRepository;
	
	@Autowired
	protected CommentRepository commentRepository;
	
	
	@RequestMapping("/categoria/{categoryId}")
	public ModelAndView categories(Model model, @PathVariable String categoryId){
		PageArticlesView pav = categoryService.getPageArticlesView(categoryId, 0);
		
		List<ArticleView> lastArticles = ArticleView.castList( articleRepository.findFirst5ByVisible(true), commentRepository );
		List<CategoryView> categories = CategoryView.castList( CategoryCommons.getCategoryList() );
		List<CommentView> lastComments = CommentView.castList( commentRepository.findFirst5ByOrderByDateInsertDesc() );
		List<ArticleView> otherArticles = ArticleView.castList( articleRepository.findRandom4() );

		model.addAttribute("category", new Category(categoryId));

		model.addAttribute("articles", pav.content);
		model.addAttribute("is_last", pav.isLast);
		
		model.addAttribute("categories", categories);
		model.addAttribute("last_comments", lastComments);
		model.addAttribute("last_articles", lastArticles);
		model.addAttribute("other_articles", otherArticles);
		
		return new ModelAndView("category");
	}
	
}
