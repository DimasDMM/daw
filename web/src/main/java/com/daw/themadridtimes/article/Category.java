package com.daw.themadridtimes.article;

public class Category {
	protected String id;
	protected String name;
	
	
	public Category() {}
	
	public Category(String id) {
		this.id = id;
		this.name = CategoryCommons.getName(id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
}
