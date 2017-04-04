package com.daw.themadridnews.article;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CategoryView {
	
	protected String id;
	protected String name;
	protected boolean isActive;
	
	
	public CategoryView( String id, String name ) {
		this.id = id;
		this.name = name;
		isActive = false;
	}

	public CategoryView(Category c) {
		this.id = c.getId();
		this.name = c.getName();
		this.isActive = false;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public static List<CategoryView> castList(List<Category> l) {
		List<CategoryView> c = new ArrayList<CategoryView>();
		Iterator<Category> it = l.iterator();
		
		while(it.hasNext())
			c.add( new CategoryView(it.next()) );
		
		return c;
	}

	public static void setActiveInList(List<CategoryView> list, String id) {
		Iterator<CategoryView> it = list.iterator();
		
		while(it.hasNext()) {
			CategoryView item = it.next();
			if(item.getId().equals( id )) {
				item.setActive(true);
				break;
			}
		}
		
	}
}
