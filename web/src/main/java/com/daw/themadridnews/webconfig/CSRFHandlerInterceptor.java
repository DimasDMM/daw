package com.daw.themadridnews.webconfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

public class CSRFHandlerInterceptor extends HandlerInterceptorAdapter {
	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if(modelAndView.getView() instanceof RedirectView) return;
		
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		
		if(token == null && request.getSession() != null)
			token = (CsrfToken) request.getSession().getAttribute("_csrf");
		
		if(token != null) {
			modelAndView.addObject("token", token.getToken());
		} else {
			modelAndView.addObject("token", "");
		}
    }
}