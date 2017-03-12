package com.daw.themadridnews.requests;

import com.daw.themadridnews.utils.Message;

public class FormSubscription extends RequestBase {
	
	protected String email;
	
	
	public FormSubscription() {
		super();
	}

	public FormSubscription(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "FormSubscription [email=" + email + "]";
	}
	
	public Message validation() {
		Message message = super.validation();
		
		if(email == null || email.isEmpty()) {
			message.setCode(1);
			message.setMessage("Por favor, introduzca un correo electronico para poder subscribirse a nuestro boletin.");
			message.setType("danger");
		}
		
		return message;
	}
}
