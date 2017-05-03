package com.daw.themadridtimes.webconfig;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import com.daw.themadridtimes.utils.Message;

@Configuration
@PropertySource("classpath:config.properties")
public class Config {
	
	public static interface Responses extends Message.Basic {}

    @Value("${path.img.absolute}") 
	private String pathImg;

    @Value("${path.img.isRelative}") 
	private boolean pathIsRelative;

    
	public String getPathImgArticles() {
		return getPathImgAbsolute()+"articles/";
	}

	public String getPathImgUsers() {
		return getPathImgAbsolute()+"users/";
	}
	
	public String getPathImgAds() {
		return getPathImgAbsolute()+"ads/";
	}
	
	public String getPathImgAbsolute() {
		String result = pathImg;
		
		if(pathIsRelative) {
			String tmp = new File(".").getAbsolutePath();
			String root = new File(tmp).getParent();
			result = new File(root, pathImg).getAbsolutePath() + "/";
		}
		
		return result;
	}
}