package com.daw.themadridnews.search;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class searchController {

	@RequestMapping("/buscar")
	public String buscar(){
		return "buscar";
	}
	
	
}
