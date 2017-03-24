package com.daw.themadridnews.requests;

import com.daw.themadridnews.favourite.Favourite;
import com.daw.themadridnews.utils.Message;

public class ApiSignup implements ApiBase {

	protected boolean terms;

	protected String alias;
	protected String name;
	protected String lastname;
	protected String email;
	protected Favourite favourites;
	protected String password;
	
	
	public ApiSignup() {}

	public ApiSignup(boolean terms, String alias, String name, String lastname, String email, Favourite favourites, String password) {
		super();
		this.terms = terms;
		this.alias = alias;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.favourites = favourites;
		this.password = password;
	}

	public boolean isTerms() {
		return terms;
	}

	public void setTerms(boolean terms) {
		this.terms = terms;
	}
	
	public String getAlias() {
		return alias;
	}
	
	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Favourite getFavourites() {
		return this.favourites;
	}
	
	public void setFavourites(Favourite favourites) {
		this.favourites = favourites;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Message validation() {
		Message message = new Message();
		
		if(!terms) {
			message.setCode(100);
			message.setMessageTxt("Es necesario que aceptes los términos de uso antes.");
			message.setType("danger");
			
		} else if(name.isEmpty() || lastname.isEmpty() || email.isEmpty() || password.isEmpty()) {
			message.setCode(101);
			message.setMessageTxt("Hay campos obligatorios sin rellenar.");
			message.setType("danger");
		}
		
		return message;
	}

	@Override
	public String toString() {
		return "ApiSignup [terms=" + terms + ", name=" + name + ", lastname=" + lastname + ", email=" + email
				+ ", password=" + password + "]";
	}
}
