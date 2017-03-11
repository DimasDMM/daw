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
		listName.add("España");
		listName.add("Mundo");
		listName.add("Deporte");
		listName.add("Tecnología");
		listName.add("Cultura");
	}

	public static List<String> getListId() {
		if(listId == null) init();
		
		return listId;
	}

	public static List<String> getListName() {
		if(listName == null) init();
		
		return listName;
	}

	public static List<Category> getCategoryList() {
		if(listId == null) init();
		
		List<Category> list = new ArrayList<Category>();
		Iterator<String> it_id = getListId().iterator();
		Iterator<String> it_name = getListName().iterator();

		while (it_id.hasNext()) {
			Category item = new Category(it_id.next(), it_name.next());
			list.add(item);
		}

		return list;
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
