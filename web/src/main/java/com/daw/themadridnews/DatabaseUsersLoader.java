package com.daw.themadridnews;

import javax.annotation.PostConstruct;

import com.daw.themadridnews.user_model.User;
import com.daw.themadridnews.user_model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUsersLoader {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void initDatabase() {

        userRepository.save(new User("pepe", "jiménez", "pepji@mail.com", "pass", "ROLE_USER"));
        userRepository.save(new User("admin", "1", "admin@mail.com", "adminpass", "ROLE_ADMIN", "ROLE_USER"));
    }

}
