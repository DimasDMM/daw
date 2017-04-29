package com.daw.themadridtimes.article;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

public class CategoryFavourites {

	public static interface Basic {}

	@JsonView(Basic.class) public String id;
	@JsonView(Basic.class) public String name;
	@JsonView(Basic.class) public List<Article> articles;
}
