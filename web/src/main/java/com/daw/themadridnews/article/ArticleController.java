package com.daw.themadridnews.article;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.daw.themadridnews.article.requests.FormComment;
import com.daw.themadridnews.comment.Comment;
import com.daw.themadridnews.comment.CommentRepository;
import com.daw.themadridnews.comment.CommentView;
import com.daw.themadridnews.user.User;
import com.daw.themadridnews.user.UserComponent;
import com.daw.themadridnews.utils.Message;

@Controller
public class ArticleController {

	@Autowired
	protected ArticleRepository articleRepository;

	@Autowired
	protected CommentRepository commentRepository;
	
	@Autowired
	protected UserComponent userComponent;
	

	@RequestMapping(value="/articulo/{id}", method=RequestMethod.GET)
	public String showArticle(Model model, HttpServletRequest request, @PathVariable long id) {
		Article article = articleRepository.findOne(id);
		
		if(article == null)
			return "redirect:/error/404";

		article.addView();
		articleRepository.save(article);

		userComponent.checkRolesAndName(model, request);
		
		List<CommentView> comments = CommentView.castList( commentRepository.findByArticle(article) );
		long nComments = commentRepository.countByArticle(article);
		
		List<Category> categories = CategoryService.getCategoryList();
		List<Article> lastArticles = articleRepository.findFirst5ByVisible(true);
		List<CommentView> lastComments = CommentView.castList( commentRepository.findFirst5ByOrderByDateInsertDesc() );

		model.addAttribute("article_id", article.getId());
		model.addAttribute("article_title", article.getTitle());
		model.addAttribute("article_content", article.getFormatedContent());
		model.addAttribute("article_tags", article.getTags());
		model.addAttribute("article_source", article.getSource());
		model.addAttribute("article_date_insert", article.getStrDateInsert());

		model.addAttribute("n_comments", nComments);
		model.addAttribute("comments", comments);
		
		model.addAttribute("categories", categories);
		model.addAttribute("last_articles", lastArticles);
		model.addAttribute("last_comments", lastComments);

		model.addAttribute("editor_name", article.getAuthor().getName());
		model.addAttribute("editor_lastname", article.getAuthor().getLastName());
		
		return "article";
	}

	@RequestMapping(value="/articulo/{id}", method=RequestMethod.POST)
	public String sendComment(Model model, HttpServletRequest request, FormComment r, @PathVariable long id) {
		
		Article article = articleRepository.findOne(id);
		if(article == null)
			return "redirect:/error/404";
		
		Message message = r.validation();
		if(message.getCode() != 0) {
			model.addAttribute("message", message);
			return showArticle(model, request, id);
		}
		
		User user = userComponent.getLoggedUser();
        long number = commentRepository.countByArticle(article) + 1;
        
        Comment comment = new Comment(article, user, number, r.getComment());
        commentRepository.save(comment);
		
		return showArticle(model, request, id);
	}
}
