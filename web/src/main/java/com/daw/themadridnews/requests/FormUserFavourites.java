package com.daw.themadridnews.requests;

import java.util.List;

import com.daw.themadridnews.utils.Message;

public class FormUserFavourites extends RequestBase {
	
	protected List<String> favs;
	
	
	public FormUserFavourites() {super();}
	
	public FormUserFavourites(List<String> favs) {
		super();
		this.favs = favs;
	}

	public List<String> getFavs() {
		return favs;
	}

	public void setFavs(List<String> favs) {
		this.favs = favs;
	}

	public Message validation() {
		Message message = new Message();
		
		return message;
	}
}
