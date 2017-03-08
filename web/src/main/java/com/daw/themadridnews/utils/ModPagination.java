package com.daw.themadridnews.utils;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;

public class ModPagination {
	
	protected int size = 5; // Tamaño de la paginacion, numero impar
	
	public List<ModPageItem> getModPageList(Page<?> page, String baseUrl) {
		
		ArrayList<ModPageItem> paginationList = new ArrayList<ModPageItem>();
		
		// Flechas iniciales
		if(!page.isFirst()) {
			paginationList.add( new ModPageItem( baseUrl+String.valueOf( page.getNumber()-1 ), "&laquo;", false ) );
		}
		
		// Paginacion
		int middle = size/2;
		for(int i = -middle-1; i <= middle+1; i++) {
			
			int n = page.getNumber() + i + 1;
			
			if(n < 1 || n > page.getTotalPages())
				continue;
			
			if(i == -middle-1 && !page.isFirst() || i == middle+1 && !page.isLast()) {
				paginationList.add( new ModPageItem( "#", "...", false ) );
				continue;
			}
			
			paginationList.add( new ModPageItem( baseUrl+String.valueOf(n), String.valueOf(n), (i==0)) );
		}
		
		// Flechas finales
		if(!page.isLast()) {
			paginationList.add( new ModPageItem( baseUrl+String.valueOf( page.getNumber()+1 ), "&raquo;", false ) );
		}
		
		return paginationList;
	}
	
	
	public class ModPageItem {
		public String url;
		public String text;
		public boolean isActive;
		
		public ModPageItem(String url, String text, boolean isActive) {
			this.url = url;
			this.text = text;
			this.isActive = isActive;
		}
	}
}
