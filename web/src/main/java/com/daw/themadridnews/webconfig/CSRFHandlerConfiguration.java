package com.daw.themadridnews.webconfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Configuration
public class CSRFHandlerConfiguration extends WebMvcConfigurerAdapter {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CSRFHandlerInterceptor()).excludePathPatterns(
				"/imagen/**", "/css/**", "/img/**", "/js/**", "/plugins/**", "/anuncio/**");
	}
}

class CSRFHandlerInterceptor extends HandlerInterceptorAdapter {
	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

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
