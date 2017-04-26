package com.daw.themadridnews.requests;

import java.util.List;

import com.daw.themadridnews.utils.Message;

public class FormSignupPreferences extends FormBase {

	protected List<String> favs;
	
	public FormSignupPreferences() {super();}
	
	public FormSignupPreferences(List<String> favs) {
		super();
		this.favs = favs;
	}

	public List<String> getFavs() {
		return favs;
	}

	public void setFavs(List<String> favs) {
		this.favs = favs;
	}

	// Devuelve true o false dependiendo de si se ha marcado el checkbox
	public boolean get(String value) {
		return favs.contains(value);
	}

	public Message validation() {
		Message message = super.validation();
		return message;
	}
}
