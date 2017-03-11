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
import com.daw.themadridnews.article.ArticleRepository;
import com.daw.themadridnews.article.ArticleView;
import com.daw.themadridnews.article.CategoryService;
import com.daw.themadridnews.article.CategoryView;
import com.daw.themadridnews.requests.FormModifyArticle;
import com.daw.themadridnews.requests.FormNewArticle;
import com.daw.themadridnews.user.User;
import com.daw.themadridnews.user.UserComponent;
import com.daw.themadridnews.utils.Message;
import com.daw.themadridnews.utils.ModPagination;
import com.daw.themadridnews.utils.ModPagination.ModPageItem;

@Controller
public class EditorController {

	@Autowired
	protected ArticleRepository articleRepository;
	
	@Autowired
	protected UserComponent userComponent;
	
	protected static final int nItemsList = 5; // Numero de articulos por pagina

	
	@RequestMapping(value="/editor/articulo/nuevo", method=RequestMethod.GET)
	public String showFormNew(Model model) {
		String newline = System.lineSeparator();
		String article_content = "Este es un texto de ejemplo con letra *cursiva* y **negrita**."+newline+newline+"~~"+newline+"Este será un"+newline+"texto lateral."+newline+"~~"+newline+newline+"[[http://url/imagen.jpg|right|Imagen lateral]]"+newline+newline+"[[http://url/imagen.jpg|full|Imagen final]]";
		
		List<CategoryView> article_categories = CategoryView.castList( CategoryService.getCategoryList() );
		
		model.addAttribute("article_id", 0);
		model.addAttribute("article_title", "");
		model.addAttribute("article_content_raw", article_content);
		model.addAttribute("article_categories", article_categories);
		model.addAttribute("article_tags_str", "");
		model.addAttribute("article_source", "");

		model.addAttribute("is_modification", false);
		model.addAttribute("is_preview", false);
		
		return "article_form";
	}
	
	@RequestMapping(value="/editor/articulo/nuevo", method=RequestMethod.POST)
	public String showFormNewPreview(Model model, FormNewArticle r) {

		User editor = userComponent.getLoggedUser();
		
		String category = r.getCategory();
		
		Article article = new Article( category, r.getTitle(), r.getContent(), editor, r.getSource(), r.getTags(), null, false );
		article = articleRepository.save(article);

		return showPreviewAux(model, article, false);
	}
	
	@RequestMapping(value="/editor/articulo/{id}", method=RequestMethod.GET)
	public String showFormModify(Model model, @PathVariable long id) {
		Article article = articleRepository.findOne(id);
		
		if(article == null) {
			Message message = new Message(1, "El articulo no existe. Por favor, seleccione uno de la lista.", "danger");
			model.addAttribute("message", message);
			return showList(model);
		}
		
		return showPreviewAux(model, article, true);
	}
	
	@RequestMapping(value="/editor/articulo/{id}/publicar", method=RequestMethod.GET)
	public String publishArticle(Model model, @PathVariable long id) {
		Article article = articleRepository.findOne(id);
		
		if(article == null) {
			Message message = new Message(1, "El articulo no existe. Por favor, seleccione uno de la lista.", "danger");
			model.addAttribute("message", message);
			return showList(model);
		}
		
		article.setVisible(true);
		articleRepository.save(article);
		
		return "redirect:/editor/articulo/lista/publicado";
	}
	
	@RequestMapping(value="/editor/articulo/{id}/ocultar", method=RequestMethod.GET)
	public String hideArticle(Model model, @PathVariable long id) {
		Article article = articleRepository.findOne(id);
		
		if(article == null) {
			Message message = new Message(1, "El articulo no existe. Por favor, seleccione uno de la lista.", "danger");
			model.addAttribute("message", message);
			return showList(model);
		}
		
		article.setVisible(false);
		articleRepository.save(article);
		
		return "redirect:/editor/articulo/lista/ocultado";
	}
	
	@RequestMapping(value="/editor/articulo/{id}/eliminar", method=RequestMethod.GET)
	public String deleteArticle(Model model, @PathVariable long id) {
		articleRepository.delete(id);
		return "redirect:/editor/articulo/lista/eliminado";
	}
	
	@RequestMapping(value="/editor/articulo/{id}", method=RequestMethod.POST)
	public String showFormModifyPreview(Model model, FormModifyArticle r, @PathVariable long id) {
		Message message;
		Article article = articleRepository.findOne(id);
		
		if(article == null) {
			message = new Message(1, "El articulo no existe. Por favor, seleccione uno de la lista.", "danger");
			model.addAttribute("message", message);
			return showList(model);
		}
		
		message = r.validation();
		if(message.getCode() == 0) {
			model.addAttribute("message", message);
			return showPreviewAux(model, article, true);
		}
		
		String category = r.getCategory();
		
		article.setCategory(category);
		article.setTitle(r.getTitle());
		article.setContent(r.getContent());
		article.setSource(r.getSource());
		article.setTags(r.getTags());
		
		article = articleRepository.save(article);
		
		return showPreviewAux(model, article, true);
	}
	
	@RequestMapping(value="/editor/articulo/lista", method=RequestMethod.GET)
	public String showList(Model model) {
		return showListAux(model, 0);
	}
	
	@RequestMapping(value="/editor/articulo/lista/publicado", method=RequestMethod.GET)
	public String showListPublished(Model model) {
		Message message = new Message(0, "El articulo ha sido publicado correctamente", "success");
		model.addAttribute("message", message);
		return showListAux(model, 0);
	}
	
	@RequestMapping(value="/editor/articulo/lista/ocultado", method=RequestMethod.GET)
	public String showListHidden(Model model) {
		Message message = new Message(0, "El articulo ha sido ocultado correctamente", "success");
		model.addAttribute("message", message);
		return showListAux(model, 0);
	}
	
	@RequestMapping(value="/editor/articulo/lista/eliminado", method=RequestMethod.GET)
	public String showListDeleted(Model model) {
		Message message = new Message(0, "El articulo ha sido eliminado correctamente", "success");
		model.addAttribute("message", message);
		return showListAux(model, 0);
	}
	
	@RequestMapping(value="/editor/articulo/lista/{nPage}", method=RequestMethod.GET)
	public String showList(Model model, @PathVariable int nPage) {
		return showListAux(model, nPage-1);
	}
	
	
	private String showPreviewAux(Model model, Article article, boolean isModification) {
		List<CategoryView> articleCategories = CategoryView.castList( CategoryService.getCategoryList() );
		CategoryView.setActiveInList( articleCategories, article.getCategory() );
		
		model.addAttribute("article_id", article.getId());
		model.addAttribute("article_title", article.getTitle());
		model.addAttribute("article_content", article.getFormatedContent());
		model.addAttribute("article_content_raw", article.getContent());
		model.addAttribute("article_categories", articleCategories);
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
		model.addAttribute("article_list", ArticleView.castList(articleList) );
		
		ModPagination modPagination = new ModPagination();
		List<ModPageItem> pageList = modPagination.getModPageList(page, "/editor/articulo/lista/");
		model.addAttribute("page_list", pageList);
		
		return "article_list";
	}

}
