package com.daw.themadridnews.User_model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
	
	List<User> findByLastName(String lastName);
	
}
