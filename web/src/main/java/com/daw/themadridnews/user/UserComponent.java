package com.daw.themadridnews.user;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;


@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserComponent {

    private User user;

    public User getLoggedUser() {
        return user;
    }

    public void setLoggedUser(User user) {
        this.user = user;
    }

    public boolean isLoggedUser() {
        return this.user != null;
    }

    public void checkRolesAndName(Model model, HttpServletRequest request) {
        model.addAttribute("user_logged", request.isUserInRole("USER"));
        model.addAttribute("editor", request.isUserInRole("EDITOR"));
        model.addAttribute("advert", request.isUserInRole("ADVERTISING"));
        User user = getLoggedUser();
        if(user!=null) {
            if(user.getAlias()!=null)
                model.addAttribute("user_logged", user.getAlias());
            else
                model.addAttribute("user_logged", user.getName());
        }
    }

    public boolean hasRole(String role) {
    	return user.getRoles().contains( role );
    }
}
