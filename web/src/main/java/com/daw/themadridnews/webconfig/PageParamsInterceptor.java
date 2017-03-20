package com.daw.themadridnews.webconfig;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

import com.daw.themadridnews.article.ArticleRepository;
import com.daw.themadridnews.article.ArticleView;
import com.daw.themadridnews.article.Category;
import com.daw.themadridnews.article.CategoryCommons;
import com.daw.themadridnews.comment.CommentRepository;

public class PageParamsInterceptor extends HandlerInterceptorAdapter {

	protected String[] weekDays = {"Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"};
	protected String[] months = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
    
    @Autowired
    protected ArticleRepository articleRepository;
    
    @Autowired
    protected CommentRepository commentRepository;
    
	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if(modelAndView == null || modelAndView.getView() instanceof RedirectView) return;
		
		List<ArticleView> footerLastArticles = ArticleView.castList( articleRepository.findFirst4ByVisible(true), commentRepository );
		modelAndView.addObject("page_footer_last_articles", footerLastArticles);
		modelAndView.addObject("page_header_date", this.getHeaderDate());
		modelAndView.addObject("page_header_menu", this.getMenuList());
	}
	
	protected String getHeaderDate() {
		StringBuilder date = new StringBuilder();
		Calendar calendar = Calendar.getInstance();
		
		date.append( weekDays[ calendar.get(Calendar.DAY_OF_WEEK)-1 ] );
		date.append(", ");
		date.append( calendar.get(Calendar.DAY_OF_MONTH) );
		date.append(" ");
		date.append( months[ calendar.get(Calendar.MONTH) ] );
		date.append(" ");
		date.append( calendar.get(Calendar.YEAR) );
		
		return date.toString();
	}
	
	protected List<CategoryItem> getMenuList() {
		List<Category> categoryList = CategoryCommons.getCategoryList();
		Iterator<Category> it_cat = categoryList.iterator();
		
		List<CategoryItem> list = new ArrayList<CategoryItem>();
		
		while(it_cat.hasNext()) {
			CategoryItem item = new CategoryItem();
			item.category = it_cat.next();
			List<ArticleView> l = ArticleView.castList( articleRepository.findFirst9ByCategoryAndVisible( item.category.getId(), true), commentRepository );
			
			Iterator<ArticleView> it_art = l.iterator();
			int i = 0;

			item.articles_col1 = new ArrayList<ArticleView>();
			item.articles_col2 = new ArrayList<ArticleView>();
			item.articles_col3 = new ArrayList<ArticleView>();
			
			while(it_art.hasNext()) {
				if(i < 3) {
					item.articles_col1.add(it_art.next());
				} else if(i < 6) {
					item.articles_col2.add(it_art.next());
				} else {
					item.articles_col3.add(it_art.next());
				}
				
				i++;
			}
			
			list.add(item);
		}
		
		return list;
	}
	
	
	/*************************/
	
	public class CategoryItem {
		public Category category;
		public List<ArticleView> articles_col1;
		public List<ArticleView> articles_col2;
		public List<ArticleView> articles_col3;
	}
}
