package com.daw.themadridnews.requests;

import com.daw.themadridnews.favourite.Favourite;
import com.daw.themadridnews.utils.Message;

public class ApiSignupStep2 implements ApiBase {

	protected Favourite favourites;
	
	
	public ApiSignupStep2() {}

	public ApiSignupStep2(Favourite favourites) {
		super();
		this.favourites = favourites;
	}
	
	public Favourite getFavourites() {
		return this.favourites;
	}
	
	public void setFavourites(Favourite favourites) {
		this.favourites = favourites;
	}

	public Message validation() {
		Message message = new Message();
		return message;
	}

	@Override
	public String toString() {
		return "ApiSignupStep2 [favourites=" + favourites + "]";
	}
}
