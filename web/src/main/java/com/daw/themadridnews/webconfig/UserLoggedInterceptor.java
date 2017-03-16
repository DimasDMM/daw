package com.daw.themadridnews.webconfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

import com.daw.themadridnews.user.User;
import com.daw.themadridnews.user.UserComponent;

public class UserLoggedInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	protected UserComponent userComponent;
	
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	if(modelAndView.getView() instanceof RedirectView) return;
    	
    	modelAndView.addObject("user_role_user", request.isUserInRole("USER") || request.isUserInRole("ADMIN"));
    	modelAndView.addObject("user_role_editor", request.isUserInRole("EDITOR") || request.isUserInRole("ADMIN"));
        modelAndView.addObject("user_role_publicist", request.isUserInRole("PUBLICIST") || request.isUserInRole("ADMIN"));
        modelAndView.addObject("user_role_admin", request.isUserInRole("ADMIN"));
        
        User userLogged = userComponent.getLoggedUser();
        if(userLogged != null) {
        	modelAndView.addObject("user_logged", true);
        	modelAndView.addObject("user_id", userLogged.getId());
        	
            if(userLogged.getAlias() != null) {
            	modelAndView.addObject("user_display", userLogged.getAlias());
            } else {
            	modelAndView.addObject("user_display", userLogged.getName());
            }
        }
    }
}
