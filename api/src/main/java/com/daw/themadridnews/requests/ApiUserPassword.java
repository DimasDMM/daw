package com.daw.themadridnews.requests;

import com.daw.themadridnews.utils.Message;

public class ApiUserPassword implements ApiBase {

	protected String pass_now;
	protected String pass_new;
	protected String pass_new2;
	
	
	public ApiUserPassword() {super();}
	
	public ApiUserPassword(String pass_now, String pass_new, String pass_new2) {
		super();
		this.pass_now = pass_now;
		this.pass_new = pass_new;
		this.pass_new2 = pass_new2;
	}

	public String getPass_now() {
		return pass_now;
	}

	public void setPass_now(String pass_now) {
		this.pass_now = pass_now;
	}

	public String getPass_new() {
		return pass_new;
	}

	public void setPass_new(String pass_new) {
		this.pass_new = pass_new;
	}

	public String getPass_new2() {
		return pass_new2;
	}

	public void setPass_new2(String pass_new2) {
		this.pass_new2 = pass_new2;
	}

	public Message validation() {
		Message message = new Message();
		
		if(pass_now == null || pass_new == null || pass_new2 == null) {
			message.setCode(100);
			message.setMessage("Hay campos en blanco. Por favor, revisa todo el formulario antes de continuar.");
			
		} else if(pass_now.isEmpty() || pass_new.isEmpty() || pass_new2.isEmpty()) {
			message.setCode(101);
			message.setMessage("Hay campos en blanco. Por favor, revisa todo el formulario antes de continuar.");
			
		} else if(!pass_new.equals(pass_new2)) {
			message.setCode(102);
			message.setMessage("Las contraseñas no coinciden. Por favor, revisela antes de continuar.");
		}
		
		return message;
	}

	@Override
	public String toString() {
		return "ApiUserPassword [pass_now=" + pass_now + ", pass_new=" + pass_new + ", pass_new2=" + pass_new2 + "]";
	}
}
