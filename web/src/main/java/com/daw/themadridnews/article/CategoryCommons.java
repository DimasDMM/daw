package com.daw.themadridnews.article;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CategoryCommons {
	
	/*
	 * Si se modifica alguna categoria, recordar modificar tambien los formularios
	 * correspondientes, como el de SettingsController
	 */

	protected static List<String> listId;
	protected static List<String> listName;

	private static void init() {
		listId = new ArrayList<String>();
		listId.add("madrid");
		listId.add("espana");
		listId.add("mundo");
		listId.add("deportes");
		listId.add("tecnologia");
		listId.add("cultura");

		listName = new ArrayList<String>();
		listName.add("Madrid");
		listName.add("España");
		listName.add("Mundo");
		listName.add("Deportes");
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

		while (it_id.hasNext()) {
			Category item = new Category(it_id.next());
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
	
	public static boolean existsCategory(String id) {
		if(listId == null) init();
		
		Iterator<String> it_id = getListId().iterator();

		while (it_id.hasNext())
			if(it_id.next().equals(id))
				return true;

		return false;
	}
}
