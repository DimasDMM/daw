package com.daw.themadridnews.editor;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.daw.themadridnews.article.Article;
import com.daw.themadridnews.article.ArticleCategoryList;
import com.daw.themadridnews.article.ArticleRepository;
import com.daw.themadridnews.article.requests.FormModifyArticle;
import com.daw.themadridnews.article.requests.FormNewArticle;
import com.daw.themadridnews.user.User;
import com.daw.themadridnews.user.UserRepository;
import com.daw.themadridnews.utils.ModPagination;
import com.daw.themadridnews.utils.ModPagination.ModPageItem;

@Controller
public class EditorController {

	@Autowired
	protected ArticleRepository articleRepository;

	@Autowired
	protected UserRepository userRepository;

	@Autowired
	protected ArticleCategoryList articleCategoryList;
	
	protected static final int nItemsList = 5; // Numero de articulos por pagina

	
	@RequestMapping(value="/editor/articulo/nuevo", method=RequestMethod.GET)
	public String showFormNew(Model model) {
		String newline = System.lineSeparator();
		String article_content = "Este es un texto de ejemplo con letra *cursiva* y **negrita**."+newline+newline+"~~"+newline+"Este será un"+newline+"texto lateral."+newline+"~~"+newline+newline+"[[http://url/imagen.jpg|right|Imagen lateral]]"+newline+newline+"[[http://url/imagen.jpg|full|Imagen final]]";
		
		model.addAttribute("article_id", 0);
		model.addAttribute("article_title", "");
		model.addAttribute("article_content_raw", article_content);
		model.addAttribute("article_categories", articleCategoryList.getCategories());
		model.addAttribute("article_tags_str", "");
		model.addAttribute("article_source", "");

		model.addAttribute("is_modification", false);
		model.addAttribute("is_preview", false);
		
		return "article_form";
	}
	
	@RequestMapping(value="/editor/articulo/nuevo", method=RequestMethod.POST)
	public String showFormNewPreview(Model model, FormNewArticle r) {

		User editor = userRepository.findByName("Jorge"); // < Obtener usuario actual
		
		Article article = new Article( r.getCategory(), r.getTitle(), r.getContent(), editor, r.getSource(), r.getTags(), false );
		article = articleRepository.save(article);

		return showPreviewAux(model, article, false);
	}
	
	@RequestMapping(value="/editor/articulo/{id}", method=RequestMethod.GET)
	public String showFormModify(Model model, @PathVariable long id) {
		Article article = articleRepository.findOne(id);
		return showPreviewAux(model, article, true);
	}
	
	@RequestMapping(value="/editor/articulo/{id}", method=RequestMethod.POST)
	public String showFormModifyPreview(Model model, FormModifyArticle r, @PathVariable long id) {
		
		Article article = articleRepository.findOne(id);
		
		article.setCategory(r.getCategory());
		article.setTitle(r.getTitle());
		article.setContent(r.getContent());
		article.setSource(r.getSource());
		article.setTags(r.getTags());
		
		article = articleRepository.save(article);
		
		return showPreviewAux(model, article, true);
	}
	
	@RequestMapping(value="/editor/articulo/listado", method=RequestMethod.GET)
	public String showList(Model model) {
		return showListAux(model, 0);
	}
	
	@RequestMapping(value="/editor/articulo/listado/{nPage}", method=RequestMethod.GET)
	public String showList(Model model, @PathVariable int nPage) {
		return showListAux(model, nPage-1);
	}
	
	
	private String showPreviewAux(Model model, Article article, boolean isModification) {
		model.addAttribute("article_id", article.getId());
		model.addAttribute("article_title", article.getTitle());
		model.addAttribute("article_content", article.getFormatedContent());
		model.addAttribute("article_content_raw", article.getContent());
		model.addAttribute("article_categories", articleCategoryList.getCategories( article.getCategory() ));
		model.addAttribute("article_tags", article.getTags());
		model.addAttribute("article_tags_str", article.getTagsStr());
		model.addAttribute("article_source", article.getSource());
		model.addAttribute("article_visible", article.isVisible());
		model.addAttribute("article_date_insert", article.getStrDateInsert());

		model.addAttribute("editor_name", article.getAuthor().getName());
		model.addAttribute("editor_lastname", article.getAuthor().getLastName());
		
		model.addAttribute("is_modification", isModification);
		model.addAttribute("is_preview", true);
		
		return "article_form";
	}
	
	private String showListAux(Model model, int nPage) {
		Page<Article> page = articleRepository.findAll( new PageRequest(nPage, nItemsList) );
		
		List<Article> articleList = page.getContent();
		model.addAttribute("article_list", articleList);
		
		ModPagination modPagination = new ModPagination();
		List<ModPageItem> pageList = modPagination.getModPageList(page, "/editor/articulo/listado/");
		model.addAttribute("page_list", pageList);
		
		return "article_list";
	}
}
