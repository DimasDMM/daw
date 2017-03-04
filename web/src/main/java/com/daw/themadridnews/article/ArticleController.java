package com.daw.themadridnews.article;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ArticleController {

	@Autowired
	protected ArticleRepository articleRepository;

	@RequestMapping("/articulo/{id}")
	public String showArticle(Model model, @PathVariable long id) {
		Article article = articleRepository.findOne(id);
		
		model.addAttribute("article_title", article.getTitle());
		model.addAttribute("article_content", article.getContent());
		
		return "article";
	}
}
