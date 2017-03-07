package com.daw.themadridnews.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.daw.themadridnews.Config;
import com.daw.themadridnews.article.requests.FormNewArticle;
import com.daw.themadridnews.user.User;

@Controller
public class ArticleController {

	@Autowired
	protected ArticleRepository articleRepository;
	
	@Autowired
	protected Config configProperties;
	

	@RequestMapping("/articulo/{id}")
	public String showArticle(Model model, @PathVariable long id) {
		Article article = articleRepository.findOne(id);
		
		User editor = article.getAuthor();

		model.addAttribute("article_id", article.getId());
		model.addAttribute("article_title", article.getTitle());
		model.addAttribute("article_content", article.getFormatedContent());
		model.addAttribute("article_tags", article.getTags());
		model.addAttribute("article_source", article.getSource());
		model.addAttribute("article_date_insert", article.getStrDateInsert());

		model.addAttribute("editor_name", editor.getName());
		model.addAttribute("editor_lastname", editor.getLastName());
		
		return "article";
	}
	
	@RequestMapping(value="/articulo/nuevo", method=RequestMethod.GET)
	public String showFormNewArticle(Model model) {
		String newline = System.lineSeparator();
		String article_content = "Este es un texto de ejemplo con letra *cursiva* y **negrita**."+newline+newline+"~~"+newline+"Este será un"+newline+"texto lateral."+newline+"~~"+newline+newline+"[[http://url/imagen.jpg|right|Imagen lateral]]"+newline+newline+"[[http://url/imagen.jpg|full|Imagen final]]";
		
		model.addAttribute("article_content_example", article_content);
		model.addAttribute("categories", configProperties.getArticleCategories());
		
		return "article_new";
	}
	
	@RequestMapping(value="/articulo/nuevo/previsualizar", method=RequestMethod.POST)
	public String showFormPreviewArticle(Model model, FormNewArticle request) {
		Article article = articleRepository.findOne((long)1); // < Se supone que me deben llegar los datos por POST
		
		User editor = article.getAuthor(); // < Obtener usuario actual

		model.addAttribute("article_id", article.getId());
		model.addAttribute("article_title", article.getTitle());
		model.addAttribute("article_content", article.getFormatedContent());
		model.addAttribute("article_content_raw", article.getContent());
		model.addAttribute("article_tags", article.getTags());
		model.addAttribute("article_source", article.getSource());
		model.addAttribute("article_date_insert", article.getStrDateInsert());

		model.addAttribute("editor_name", editor.getName());
		model.addAttribute("editor_lastname", editor.getLastName());

		model.addAttribute("categories", configProperties.getArticleCategories());
		
		return "article_new_preview";
	}
}
