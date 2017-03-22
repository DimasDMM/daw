package com.daw.themadridnews.requests;

import com.daw.themadridnews.utils.Message;

public class FormUserPersonal extends RequestBase {

	protected String name;
	protected String lastname;
	protected String alias;
	protected String email;
	protected char sex;
	
	protected String city;
	protected String country;
	protected String phone;
	protected String description;
	protected String url;
	
	
	
	public FormUserPersonal() {super();}
	
	public FormUserPersonal(String name, String lastname, String alias, String email, char sex, String city,
			String country, String phone, String description, String url) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.alias = alias;
		this.email = email;
		this.sex = sex;
		this.city = city;
		this.country = country;
		this.phone = phone;
		this.description = description;
		this.url = url;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "FormUserPersonal [name=" + name + ", lastname=" + lastname + ", alias=" + alias + ", email=" + email
				+ ", sex=" + sex + ", city=" + city + ", country=" + country + ", phone=" + phone + ", description="
				+ description + ", url=" + url + "]";
	}

	public Message validation() {
		Message message = super.validation();
		if(message.getCode() != 0)
			return message;
		
		if(name.isEmpty() || lastname.isEmpty() || email.isEmpty()) {
			message.setCode(100);
			message.setMessage("Hay campos en blanco. Por favor, revisa todo el formulario antes de continuar.");
			message.setType("danger");
		}
		
		return message;
	}
}
