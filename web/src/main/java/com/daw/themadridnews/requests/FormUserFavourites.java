package com.daw.themadridnews.requests;

import java.util.List;

import com.daw.themadridnews.utils.Message;

public class FormUserFavourites extends RequestBase {
	
	protected String favs[];
	
	
	public FormUserFavourites() {super();}
	
	public FormUserFavourites(String[] favs) {
		super();
		this.favs = favs;
	}

	public String[] getFavs() {
		return favs;
	}

	public void setFavs(String[] favs) {
		this.favs = favs;
	}

	public Message validation() {
		Message message = new Message();
		
		return message;
	}
}
