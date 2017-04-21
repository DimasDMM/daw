package com.daw.themadridnews;

import java.io.IOException;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
//import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@Bean
	@SuppressWarnings("rawtypes")
	public Module springDataPageModule() {
	    return new SimpleModule().addSerializer(Page.class, new JsonSerializer<Page>() {
	        @Override
	        public void serialize(Page value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
	            gen.writeStartObject();
	            gen.writeNumberField("totalElements",value.getTotalElements());
	            gen.writeNumberField("totalPages", value.getTotalPages());
	            gen.writeNumberField("number", value.getNumber());
	            gen.writeNumberField("size", value.getSize());
	            gen.writeBooleanField("first", value.isFirst());
	            gen.writeBooleanField("last", value.isLast());
	            gen.writeFieldName("content");
	            serializers.defaultSerializeValue(value.getContent(),gen);
	            gen.writeEndObject();
	        }
	    });
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
	/*
	@Bean
	public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("12MB");
        factory.setMaxRequestSize("12MB");
        return factory.createMultipartConfig();
    }
	*/
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            	registry.addMapping("/**").allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE")
            		.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
            		.allowCredentials(true).maxAge(3600);
            }
        };
    }
}
