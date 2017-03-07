package com.daw.themadridnews.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daw.themadridnews.user.User;

@Controller
public class ArticleController {

	@Autowired
	protected ArticleRepository articleRepository;

	@RequestMapping("/articulo/{id}")
	public String showArticle(Model model, @PathVariable long id) {
		Article article = articleRepository.findOne(id);
		
		User editor = article.getAuthor();
		
		model.addAttribute("article_title", article.getTitle());
		model.addAttribute("article_content", article.getFormatedContent());
		model.addAttribute("article_tags", article.getTags());
		model.addAttribute("article_source", article.getSource());
		model.addAttribute("article_date_insert", article.getStrDateInsert());

		model.addAttribute("editor_name", editor.getName());
		model.addAttribute("editor_lastname", editor.getLastName());
		
		return "article";
	}
}
