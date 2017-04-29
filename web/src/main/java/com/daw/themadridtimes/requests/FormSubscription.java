package com.daw.themadridtimes.requests;

import com.daw.themadridtimes.utils.Message;

public class FormSubscription extends FormBase {
	
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
		if(message.getCode() != 0)
			return message;
		
		if(email == null || email.isEmpty()) {
			message.setCode(100);
			message.setMessage("Por favor, introduzca un correo electronico para poder subscribirse a nuestro boletin.");
		}
		
		return message;
	}
}
