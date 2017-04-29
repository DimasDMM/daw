package com.daw.themadridtimes.requests;

import com.daw.themadridtimes.utils.Message;

public class ApiSignupStep1 implements ApiBase {

	protected boolean terms;
	protected String name;
	protected String lastname;
	protected String email;
	protected String password1;
	protected String password2;
	
	
	public ApiSignupStep1() {}

	public ApiSignupStep1(boolean terms, String name, String lastname, String email, String password1,
			String password2) {
		super();
		this.terms = terms;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password1 = password1;
		this.password2 = password2;
	}

	public boolean isTerms() {
		return terms;
	}

	public void setTerms(boolean terms) {
		this.terms = terms;
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

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public Message validation() {
		Message message = new Message();
		
		if(!terms) {
			message.setCode(100);
			message.setMessage("Es necesario que aceptes los términos de uso antes.");
			
		} else if(
				name == null || name.isEmpty() ||
				lastname == null || lastname.isEmpty() ||
				email == null || email.isEmpty() ||
				password1 == null || password1.isEmpty() ||
				password2 == null || password2.isEmpty()
		) {
			message.setCode(101);
			message.setMessage("Hay campos obligatorios sin rellenar.");
			
		} else if(!password1.equals( password2 )) {
			message.setCode(102);
			message.setMessage("Las contraseñas no coinciden. Por favor, revise los datos antes de continuar.");
		}
		
		return message;
	}

	@Override
	public String toString() {
		return "ApiSignupStep1 [terms=" + terms + ", name=" + name + ", lastname=" + lastname + ", email=" + email
				+ ", password1=" + password1 + ", password2=" + password2 + "]";
	}
}
