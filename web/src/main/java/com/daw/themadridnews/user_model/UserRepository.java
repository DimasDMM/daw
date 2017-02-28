package com.daw.themadridnews.user_model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	
	List<UserEntity> findByLastName(String lastName);
	
}
