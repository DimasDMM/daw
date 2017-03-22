package com.daw.themadridnews.requests;

import java.util.List;

import com.daw.themadridnews.utils.Message;

public class FormAdminUser extends RequestBase {
	
	protected String name;
	protected String lastname;
	protected String alias;
	protected List<String> roles;
	
	
	public FormAdminUser() {super();}

	public FormAdminUser(String name, String lastname, String alias, List<String> roles) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.alias = alias;
		this.roles = roles;
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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "FormAdminUser [name=" + name + ", lastname=" + lastname + ", alias=" + alias + ", roles=" + roles + "]";
	}

	public Message validation() {
		Message message = super.validation();
		
		return message;
	}
}
