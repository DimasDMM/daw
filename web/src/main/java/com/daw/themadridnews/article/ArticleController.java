package com.daw.themadridnews.article;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.daw.themadridnews.comment.Comment;
import com.daw.themadridnews.comment.CommentRepository;
import com.daw.themadridnews.comment.CommentService;
import com.daw.themadridnews.comment.CommentView;
import com.daw.themadridnews.requests.FormComment;
import com.daw.themadridnews.user.User;
import com.daw.themadridnews.user.UserComponent;
import com.daw.themadridnews.utils.Message;

@Controller
public class ArticleController {
	
	@Autowired
	protected ArticleService articleService;

	@Autowired
	protected ArticleRepository articleRepository;

	@Autowired
	protected CommentService commentService;
	
	@Autowired
	protected CommentRepository commentRepository;
	
	@Autowired
	protected UserComponent userComponent;
	

	@RequestMapping(value="/articulo/{id}", method=RequestMethod.GET)
	public ModelAndView showArticle(Model model, @PathVariable long id) {
		Article a = articleService.get(id);
		articleService.addView(a);
		
		if(a == null)
			return new ModelAndView( new RedirectView("/error/404") );
		
		List<CommentView> comments = CommentView.castList( commentService.get(a) );
		long nComments = commentService.countByArticle(a);

		List<ArticleView> lastArticles = ArticleView.castList( articleRepository.findFirst5ByVisible(true), commentRepository );
		List<CategoryView> categories = CategoryView.castList( CategoryCommons.getCategoryList() );
		List<CommentView> lastComments = CommentView.castList( commentService.getLastComments() );
		List<ArticleView> otherArticles = ArticleView.castList( articleRepository.findRandom4() );
		
		ArticleView av = new ArticleView(a);

		model.addAttribute("article_id", av.getId());
		model.addAttribute("article_title", av.getTitle());
		model.addAttribute("article_content", av.getFormatedContent());
		model.addAttribute("article_tags", av.getTags());
		model.addAttribute("article_source", av.getSource());
		model.addAttribute("article_date_insert", av.getDateInsertStrLong());

		model.addAttribute("n_comments", nComments);
		model.addAttribute("comments", comments);
		
		model.addAttribute("categories", categories);
		model.addAttribute("last_comments", lastComments);
		model.addAttribute("last_articles", lastArticles);
		model.addAttribute("other_articles", otherArticles);

		model.addAttribute("editor_name", av.getAuthor().getName());
		model.addAttribute("editor_lastname", av.getAuthor().getLastname());

		return new ModelAndView("article");
	}

	@RequestMapping(value="/articulo/{id}", method=RequestMethod.POST)
	public ModelAndView sendComment(Model model, FormComment r, @PathVariable long id) {
		Message message = r.validation();
		if(message.getCode() != 0) {
			model.addAttribute("message", message);
			return showArticle(model, id);
		}
		
		Article article = articleRepository.findOne(id);
		if(article == null)
			return new ModelAndView( new RedirectView("/error/404") );
		
		User user = userComponent.getLoggedUser();
        long number = commentRepository.countByArticle(article) + 1;
        Comment comment = new Comment(article, user, number, r.getComment());
		
		commentService.save(comment);
		
		return showArticle(model, id);
	}
}
