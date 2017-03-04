package com.daw.themadridnews.user_model;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
	
	/*@Autowired
	private UserRepository repository;*/
	
	/*@PostConstruct
	public void init(){
		List <String> roles=new ArrayList<String>();
		roles.add("USER");
		repository.save(new User("Paco", "Pérez","pape","paco@gamil.com", "paco1", roles));
		repository.save(new User("Pepe", "Jiménez","peji","peji@gamil.com", "pepe1", roles));
	}*/
	@RequestMapping("/user-settings")
	public String getUsers(Model model,@RequestParam(required=false) String user) {		
		model.addAttribute("userLogin","rafael07");
		return "user-settings";
	}

	@RequestMapping("/user_sign")
	public String user_sign(Model model) {
		return "user_sign";
	}
}
