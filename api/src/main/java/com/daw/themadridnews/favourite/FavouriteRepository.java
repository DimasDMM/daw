package com.daw.themadridnews.favourite;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteRepository extends JpaRepository<Favourite, Long>{
	
}
