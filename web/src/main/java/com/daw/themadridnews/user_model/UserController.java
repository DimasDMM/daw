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
