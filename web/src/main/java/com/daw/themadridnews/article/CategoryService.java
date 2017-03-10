package com.daw.themadridnews.article;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CategoryService {

	protected static List<String> listId;
	protected static List<String> listName;

	private static void init() {
		listId = new ArrayList<String>();
		listId.add("madrid");
		listId.add("spain");
		listId.add("world");
		listId.add("sport");
		listId.add("technology");
		listId.add("culture");

		listName = new ArrayList<String>();
		listName.add("Madrid");
		listName.add("Spain");
		listName.add("World");
		listName.add("Sport");
		listName.add("Technology");
		listName.add("Culture");
	}

	public static List<String> getListId() {
		if(listId == null) init();
		
		return listId;
	}

	public static List<String> getListName() {
		if(listId == null) init();
		
		return listId;
	}

	public static List<Category> getCategoryList() {
		if(listId == null) init();
		
		List<Category> articleCategories = new ArrayList<Category>();
		Iterator<String> it_id = getListId().iterator();
		Iterator<String> it_name = getListName().iterator();

		while (it_id.hasNext()) {
			Category item = new Category();
			item.id = it_id.next();
			item.name = it_name.next();

			articleCategories.add(item);
		}

		return articleCategories;
	}

	public static String getName(String id) {
		if(listId == null) init();
		
		Iterator<String> it_id = getListId().iterator();
		Iterator<String> it_name = getListName().iterator();

		String name;
		while (it_id.hasNext()) {
			name = it_name.next();
			
			if(it_id.next().equals(id))
				return name;
		}

		return "Desconocido";
	}
}
