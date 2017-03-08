package com.daw.themadridnews;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.Bean;
import com.daw.themadridnews.article.*;

@SpringBootApplication
public class App {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@Bean
	public ArticleCategoryList ArticleCategoryList() {
		return new ArticleCategoryList();
	}
}
