package com.daw.themadridnews.user;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
	
	List<User> findByLastName(String lastName);
	User findByName(String name);
	User findByEmail(String email);
	
}
