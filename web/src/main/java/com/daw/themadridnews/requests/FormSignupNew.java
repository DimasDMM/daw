package com.daw.themadridnews.requests;

import com.daw.themadridnews.utils.Message;

public class FormSignupNew extends RequestBase {

	protected boolean terms;
	protected String name;
	protected String lastName;
	protected String email;
	protected String pass_new1;
	protected String pass_new2;
	
	
	public FormSignupNew() {super();}

	public FormSignupNew(boolean terms, String name, String lastName, String email, String pass_new1, String pass_new2) {
		super();
		this.terms = terms;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.pass_new1 = pass_new1;
		this.pass_new2 = pass_new2;
	}
	
	public boolean getTerms() {
		return terms;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPass_new1() {
		return pass_new1;
	}

	public String getPass_new2() {
		return pass_new2;
	}

	public Message validation() {
		Message message = new Message();
		
		System.out.println("### "+ this.toString());
		
		if(!terms) {
			message.setCode(1);
			message.setMessage("Es necesario que aceptes los términos de uso antes de enviar el formulario.");
			message.setType("danger");
			
		} else if(name.isEmpty() || lastName.isEmpty() || email.isEmpty() || pass_new1.isEmpty() || pass_new2.isEmpty()) {
			message.setCode(2);
			message.setMessage("Hay campos en blanco. Por favor, revisa todo el formulario antes de continuar.");
			message.setType("danger");
			
		} else if(!pass_new1.equals(pass_new2)) {
			message.setCode(3);
			message.setMessage("Las contraseñas no coinciden. Por favor, revisela antes de continuar.");
			message.setType("danger");
		}

		System.out.println("### "+ message.toString());
		
		return message;
	}

	@Override
	public String toString() {
		return "FormSignupNew [terms=" + terms + ", name=" + name + ", lastName=" + lastName + ", email=" + email
				+ ", pass_new1=" + pass_new1 + ", pass_new2=" + pass_new2 + "]";
	}
}
