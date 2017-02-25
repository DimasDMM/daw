package com.daw.themadridnews;


import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.daw.themadridnews.User_model.*;

@Controller
public class HolaController {
	
	@Autowired
	private UserRepository repository;
	
	@RequestMapping("/hola")
	public String Hola(Model model) {
		model.addAttribute("name","Mundo");
		return "hola_template";
	}
	@PostConstruct
	public void init(){
		repository.save(new User("Paco", "Pérez","pape","paco@gamil.com"));
		repository.save(new User("Pepe", "Jiménez","peji","peji@gamil.com"));
	}
	@RequestMapping("/users")
	public String getUsers(Model model) {		
		model.addAttribute("user1",repository.findAll());
		return "users";
	}
}
