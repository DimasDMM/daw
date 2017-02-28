package com.daw.themadridnews;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HolaController {
	
	@RequestMapping("/hola")
	public String Hola(Model model) {
		model.addAttribute("name","Mundo");
		return "hola_template";
	}
	
}
