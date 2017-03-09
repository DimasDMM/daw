package com.daw.themadridnews.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {
	
	@RequestMapping("/category/{cat}")
	public String categories(@PathVariable String cat){
		
		return cat;
		
	}
	
}
