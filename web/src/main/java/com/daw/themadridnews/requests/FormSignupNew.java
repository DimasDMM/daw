package com.daw.themadridnews.requests;

import com.daw.themadridnews.utils.Message;

public class FormSignupNew extends RequestBase {

	protected boolean terms;
	protected String name;
	protected String lastname;
	protected String email;
	protected String pass_new1;
	protected String pass_new2;
	
	
	public FormSignupNew() {super();}

	public FormSignupNew(boolean terms, String name, String lastname, String email, String pass_new1, String pass_new2) {
		super();
		this.terms = terms;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.pass_new1 = pass_new1;
		this.pass_new2 = pass_new2;
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

	public String getPass_new1() {
		return pass_new1;
	}

	public void setPass_new1(String pass_new1) {
		this.pass_new1 = pass_new1;
	}

	public String getPass_new2() {
		return pass_new2;
	}

	public void setPass_new2(String pass_new2) {
		this.pass_new2 = pass_new2;
	}

	public Message validation() {
		Message message = super.validation();
		if(message.getCode() != 0)
			return message;
		
		if(!terms) {
			message.setCode(100);
			message.setMessage("Es necesario que aceptes los términos de uso antes de enviar el formulario.");
			message.setType("danger");
			
		} else if(name.isEmpty() || lastname.isEmpty() || email.isEmpty() || pass_new1.isEmpty() || pass_new2.isEmpty()) {
			message.setCode(101);
			message.setMessage("Hay campos en blanco. Por favor, revisa todo el formulario antes de continuar.");
			message.setType("danger");
			
		} else if(!pass_new1.equals(pass_new2)) {
			message.setCode(102);
			message.setMessage("Las contraseñas no coinciden. Por favor, revisela antes de continuar.");
			message.setType("danger");
		}
		
		return message;
	}

	@Override
	public String toString() {
		return "FormSignupNew [_csrf=" + _csrf + ", terms=" + terms + ", name=" + name + ", lastname=" + lastname + ", email=" + email
				+ ", pass_new1=" + pass_new1 + ", pass_new2=" + pass_new2 + "]";
	}
}
