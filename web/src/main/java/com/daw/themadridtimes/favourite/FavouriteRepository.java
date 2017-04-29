package com.daw.themadridtimes.favourite;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteRepository extends JpaRepository<Favourite, Long>{
	
}
