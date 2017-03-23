package com.daw.themadridnews.requests;

import com.daw.themadridnews.utils.Message;

public class ApiSubscription implements ApiBase {
	
	protected String email;
	
	
	public ApiSubscription() {
		super();
	}

	public ApiSubscription(String email) {
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
		return "ApiSubscription [email=" + email + "]";
	}
	
	public Message validation() {
		Message message = new Message();
		
		if(email == null || email.isEmpty()) {
			message.setCode(100);
			message.setMessage("Por favor, introduzca un correo electronico para poder subscribirse a nuestro boletin.");
			message.setType("danger");
		} else if(Validator.strValidMail(email)) {
			message.setCode(101);
			message.setMessage("Por favor, introduzca un correo electronico valido.");
			message.setType("danger");
		}
		
		return message;
	}
}
