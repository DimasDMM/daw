package com.daw.themadridnews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daw.themadridnews.article.ArticleRepository;
import com.daw.themadridnews.article.ArticleView;
import com.daw.themadridnews.comment.CommentRepository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {
	
	private static final String PATH = "/error";

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private Config config;
	
	@Autowired
	private ErrorService errorService;
		  
	@RequestMapping(value="/error")
	public String renderErrorPage(Model model, HttpServletRequest request){
		
		// Http error code
		int code = getHttpStatusCode(request);
		
		return renderErrorCodePage(model, request, code);
	}
	  
	@RequestMapping(value="/error/{code}")
	public String renderErrorCodePage(Model model, HttpServletRequest request, @PathVariable int code){

		model.addAttribute("page_header_date", config.getHeaderDate());
		model.addAttribute("page_header_menu", config.getMenuList());

		List<ArticleView> footerLastArticles = ArticleView.castList( articleRepository.findFirst4ByVisible(true), commentRepository );
		model.addAttribute("page_footer_last_articles", footerLastArticles);
		model.addAttribute("page_header_date", config.getHeaderDate());
		model.addAttribute("page_header_menu", config.getMenuList());
		
		String errorMessage = errorService.generateErrorMessage(code);
		
		model.addAttribute("error_msg", errorMessage);
		return "error_page";
	}
	
	private int getHttpStatusCode(HttpServletRequest request){
		return (int) request.getAttribute("javax.servlet.error.status_code");
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}
}