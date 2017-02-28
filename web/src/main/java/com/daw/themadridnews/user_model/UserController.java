package com.daw.themadridnews.user_model;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository repository;
	
	@PostConstruct
	public void init(){
		repository.save(new UserEntity("Paco", "Pérez","pape","paco@gamil.com"));
		repository.save(new UserEntity("Pepe", "Jiménez","peji","peji@gamil.com"));
	}
	@RequestMapping("/user-settings")
	public String getUsers(Model model,@RequestParam(required=false) String user) {		
		model.addAttribute("userLogin","rafael07");
		return "user-settings";
	}
}
