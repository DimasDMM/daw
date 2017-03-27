package com.daw.themadridnews.editor;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.daw.themadridnews.article.Article;
import com.daw.themadridnews.article.ArticleService;
import com.daw.themadridnews.article.ArticleView;
import com.daw.themadridnews.article.CategoryCommons;
import com.daw.themadridnews.article.CategoryView;
import com.daw.themadridnews.files.FileUploadCommons;
import com.daw.themadridnews.requests.FormModifyArticle;
import com.daw.themadridnews.requests.FormNewArticle;
import com.daw.themadridnews.user.UserService;
import com.daw.themadridnews.utils.Message;
import com.daw.themadridnews.utils.ModPagination;
import com.daw.themadridnews.utils.ModPagination.ModPageItem;
import com.daw.themadridnews.webconfig.Config;

@Controller
public class EditorController {

	@Autowired
	protected ArticleService articleService;
	
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected Config config;

	
	@RequestMapping(value="/editor/articulo/nuevo", method=RequestMethod.GET)
	public ModelAndView showFormNew(Model model) {
		String newline = System.lineSeparator();
		String article_content = "Este es un texto de ejemplo con letra *cursiva* y **negrita**."+newline+newline+"~~"+newline+"Este será un"+newline+"texto lateral."+newline+"~~"+newline+newline+"[[http://url/imagen.jpg|right|Imagen lateral]]"+newline+newline+"[[http://url/imagen.jpg|full|Imagen final]]";
		
		List<CategoryView> article_categories = CategoryView.castList( CategoryCommons.getCategoryList() );
		
		model.addAttribute("article_id", 0);
		model.addAttribute("article_title", "");
		model.addAttribute("article_content_raw", article_content);
		model.addAttribute("article_categories", article_categories);
		model.addAttribute("article_tags_str", "");
		model.addAttribute("article_source", "");

		model.addAttribute("is_modification", false);
		model.addAttribute("is_preview", false);
		
		return new ModelAndView("article_form");
	}
	
	@RequestMapping(value="/editor/articulo/nuevo", method=RequestMethod.POST)
	public ModelAndView showFormNewPreview(Model model, FormNewArticle r, @RequestParam("file") MultipartFile file) {
		Message message = r.validation();
		if(message.getCode() != 0) {
			model.addAttribute("message", message);
			return showFormNew(model);
		}

		String category = r.getCategory();
		
		Article article = new Article( category, r.getTitle(), r.getContent(), userService.getLoggedUser(), r.getSource(), r.getTags(), null, false );
		article = articleService.save(article);
		
		FileUploadCommons.saveImage( file, config.getPathImgArticles(), String.valueOf(article.getId()) );

		return showPreviewAux(model, article, false);
	}
	
	@RequestMapping(value="/editor/articulo/{id}", method=RequestMethod.GET)
	public ModelAndView showFormModify(Model model, @PathVariable long id) {
		Article article = articleService.get(id);
		
		if(article == null) {
			Message message = new Message(1, "El articulo no existe. Por favor, seleccione uno de la lista.", "danger");
			model.addAttribute("message", message);
			return showList(model);
		}
		
		return showPreviewAux(model, article, true);
	}
	
	@RequestMapping(value="/editor/articulo/{id}/publicar", method=RequestMethod.GET)
	public ModelAndView publishArticle(Model model, @PathVariable long id) {
		Article article = articleService.get(id);
		
		if(article == null) {
			Message message = new Message(1, "El articulo no existe. Por favor, seleccione uno de la lista.", "danger");
			model.addAttribute("message", message);
			return showList(model);
		}
		
		article.setVisible(true);
		articleService.save(article);

		return new ModelAndView( new RedirectView("/editor/articulo/lista/publicado") );
	}
	
	@RequestMapping(value="/editor/articulo/{id}/ocultar", method=RequestMethod.GET)
	public ModelAndView hideArticle(Model model, @PathVariable long id) {
		Article article = articleService.get(id);
		
		if(article == null) {
			Message message = new Message(1, "El articulo no existe. Por favor, seleccione uno de la lista.", "danger");
			model.addAttribute("message", message);
			return showList(model);
		}
		
		article.setVisible(false);
		articleService.save(article);
		
		return new ModelAndView( new RedirectView("/editor/articulo/lista/ocultado") );
	}
	
	@RequestMapping(value="/editor/articulo/{id}/eliminar", method=RequestMethod.GET)
	public ModelAndView deleteArticle(Model model, @PathVariable long id) {
		Article a = articleService.get(id);
		articleService.delete(a);
		return new ModelAndView( new RedirectView("/editor/articulo/lista/eliminado") );
	}
	
	@RequestMapping(value="/editor/articulo/{id}", method=RequestMethod.POST)
	public ModelAndView showFormModifyPreview(Model model, FormModifyArticle r, @PathVariable long id, @RequestParam("file") MultipartFile file) {
		Message message;
		Article article = articleService.get(id);
		
		if(article == null) {
			message = new Message(1, "El articulo no existe. Por favor, seleccione uno de la lista.", "danger");
			model.addAttribute("message", message);
			return showList(model);
		}
		
		message = r.validation();
		if(message.getCode() != 0) {
			model.addAttribute("message", message);
			return showPreviewAux(model, article, true);
		}
		
		String category = r.getCategory();
		
		article.setCategory(category);
		article.setTitle(r.getTitle());
		article.setContent(r.getContent());
		article.setSource(r.getSource());
		article.setTags(r.getTags());
		
		article = articleService.save(article);
		
		FileUploadCommons.saveImage( file, config.getPathImgArticles(), String.valueOf(article.getId()) );
		
		return showPreviewAux(model, article, true);
	}
	
	@RequestMapping(value="/editor/articulo/lista", method=RequestMethod.GET)
	public ModelAndView showList(Model model) {
		return showListAux(model, 0);
	}
	
	@RequestMapping(value="/editor/articulo/lista/{nPage}", method=RequestMethod.GET)
	public ModelAndView showList(Model model, @PathVariable int nPage) {
		return showListAux(model, nPage-1);
	}
	
	@RequestMapping(value="/editor/articulo/lista/publicado", method=RequestMethod.GET)
	public ModelAndView showListPublished(Model model) {
		Message message = new Message(0, "El articulo ha sido publicado correctamente", "success");
		model.addAttribute("message", message);
		return showListAux(model, 0);
	}
	
	@RequestMapping(value="/editor/articulo/lista/ocultado", method=RequestMethod.GET)
	public ModelAndView showListHidden(Model model) {
		Message message = new Message(0, "El articulo ha sido ocultado correctamente", "success");
		model.addAttribute("message", message);
		return showListAux(model, 0);
	}
	
	@RequestMapping(value="/editor/articulo/lista/eliminado", method=RequestMethod.GET)
	public ModelAndView showListDeleted(Model model) {
		Message message = new Message(0, "El articulo ha sido eliminado correctamente", "success");
		model.addAttribute("message", message);
		return showListAux(model, 0);
	}
	
	private ModelAndView showPreviewAux(Model model, Article a, boolean isModification) {
		ArticleView av = new ArticleView(a);
		
		List<CategoryView> articleCategories = CategoryView.castList( CategoryCommons.getCategoryList() );
		CategoryView.setActiveInList( articleCategories, av.getCategory().getId() );
		
		model.addAttribute("article_id", av.getId());
		model.addAttribute("article_title", av.getTitle());
		model.addAttribute("article_content", av.getFormatedContent());
		model.addAttribute("article_content_raw", av.getContent());
		model.addAttribute("article_categories", articleCategories);
		model.addAttribute("article_tags", av.getTags());
		model.addAttribute("article_tags_str", av.getTagsStr());
		model.addAttribute("article_source", av.getSource());
		model.addAttribute("article_visible", av.isVisible());
		model.addAttribute("article_date_insert", av.getDateInsertStrLong());

		model.addAttribute("editor_name", av.getAuthor().getName());
		model.addAttribute("editor_lastname", av.getAuthor().getLastname());
		
		model.addAttribute("is_modification", isModification);
		model.addAttribute("is_preview", true);
		
		return new ModelAndView("article_form");
	}
	
	private ModelAndView showListAux(Model model, int nPage) {
		Page<Article> page = articleService.listWhenPermission(nPage);
		
		List<Article> articleList = page.getContent();
		model.addAttribute("article_list", ArticleView.castList(articleList) );
		
		ModPagination modPagination = new ModPagination();
		List<ModPageItem> pageList = modPagination.getModPageList(page, "/editor/articulo/lista/");
		model.addAttribute("page_list", pageList);
		
		return new ModelAndView("article_list");
	}

}
