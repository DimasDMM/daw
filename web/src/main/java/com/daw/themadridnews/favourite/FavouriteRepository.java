package com.daw.themadridnews.favourite;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteRepository extends JpaRepository<Favourite, Long>{
    
}
