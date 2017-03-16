package com.daw.themadridnews.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByName(String name);
	User findByEmail(String name);
}
