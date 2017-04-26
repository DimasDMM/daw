package com.daw.themadridnews.webconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(userLoggedInterceptor()).excludePathPatterns(
				"/imagen/**", "/css/**", "/img/**", "/js/**", "/plugins/**");

		registry.addInterceptor(pageParamsInterceptor()).excludePathPatterns(
				"/imagen/**", "/css/**", "/img/**", "/js/**", "/plugins/**");
	}
	
	@Bean
	protected UserLoggedInterceptor userLoggedInterceptor() {
	    return new UserLoggedInterceptor();
	}
	
	@Bean
	protected PageParamsInterceptor pageParamsInterceptor() {
	    return new PageParamsInterceptor();
	}
}
