package com.daw.themadridnews;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import com.daw.themadridnews.article.Article;
import com.daw.themadridnews.article.ArticleRepository;
import com.daw.themadridnews.user_model.User;
import com.daw.themadridnews.user_model.UserRepository;

@Controller
public class DataExampleController implements CommandLineRunner {

	@Autowired
	private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
        userRepository.save(new User("pepe", "jiménez", "pepji@mail.com", "pass", "ROLE_USER"));
        userRepository.save(new User("Jorge", "Injusto", "justamente@mail.com", "pass", "ROLE_EDITOR", "ROLE_USER"));
        userRepository.save(new User("admin", "1", "admin@mail.com", "adminpass", "ROLE_ADMIN", "ROLE_USER"));
        
        ArrayList<String> tags = new ArrayList<String>();
        User editor = userRepository.findByName("Jorge");
        articleRepository.save(new Article("madrid", "El alicatador sigue fugado", "El alicatador alicatante sigue en busca y captura", editor, "fuente.com", tags));
	}
}