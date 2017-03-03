package com.daw.themadridnews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import com.daw.themadridnews.article.ArticleRepository;

@Controller
public class DataExampleController implements CommandLineRunner {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
	}
}