package com.daw.themadridnews.webconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
public class Config {

    @Value("${path.img.absolute}") 
	private String pathImgAbsolute;
	
    @Value("${path.img.articles}") 
    private String pathImgArticles;

    @Value("${path.img.users}") 
    private String pathImgUsers;

    @Value("${path.img.ads}") 
    private String pathImgAds;

    
	public String getPathImgArticles() {
		return pathImgArticles;
	}

	public String getPathImgUsers() {
		return pathImgUsers;
	}
	
	public String getPathImgAds() {
		return pathImgAds;
	}
	
	public String getPathImgAbsolute() {
		return pathImgAbsolute;
	}
}