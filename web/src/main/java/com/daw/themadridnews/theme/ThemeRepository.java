package com.daw.themadridnews.theme;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Long>{
    
}
