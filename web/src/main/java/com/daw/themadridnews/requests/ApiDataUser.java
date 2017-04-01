package com.daw.themadridnews.requests;

import com.daw.themadridnews.favourite.Favourite;
import com.daw.themadridnews.utils.Message;
import java.util.ArrayList;
import java.util.List;

public class ApiDataUser implements ApiBase {

	private Long id;
	private String alias;
	private String name;
	private String lastname;
	private String email;
	
	private List<String> roles;
	private Favourite favourites;

	private Character sex;
	private String city;
	private String country;
	private String phoneNumber;
	private String description;
	private String personalWeb;


	public ApiDataUser() {
		this.roles = new ArrayList<String>();
		this.favourites = new Favourite();
	}

	public ApiDataUser(String alias, String name, String lastname, String email, List<String> roles,
			Favourite favourites, Character sex, String city, String country, String phoneNumber, String description,
			String personalWeb) {
		this.alias = alias;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.roles = roles;
		this.favourites = favourites;
		this.sex = sex;
		this.city = city;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.description = description;
		this.personalWeb = personalWeb;
	}

	public ApiDataUser(Long id, String alias, String name, String lastname, String email, List<String> roles,
			Favourite favourites, Character sex, String city, String country, String phoneNumber, String description,
			String personalWeb) {
		this(alias, name, lastname, email, roles, favourites, sex, city, country, phoneNumber, description, personalWeb);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public Favourite getFavourites() {
		return favourites;
	}

	public void setFavourites(Favourite favourites) {
		this.favourites = favourites;
	}

	public Character getSex() {
		return sex;
	}

	public void setSex(Character sex) {
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPersonalWeb() {
		return personalWeb;
	}

	public void setPersonalWeb(String personalWeb) {
		this.personalWeb = personalWeb;
	}

	public boolean hasRole(String role) {
		return this.getRoles().contains( role );
	}

	public Message validation() {
		Message message = new Message();
		
		System.out.print( "#### "+ this.toString() );
		
		if(name.isEmpty() || lastname.isEmpty() || email.isEmpty()) {
			message.setCode(100);
			message.setMessageTxt("Hay campos en blanco. Por favor, revisa todo el formulario antes de continuar.");
			message.setType("danger");
		}
		
		return message;
	}

	@Override
	public String toString() {
		return "ApiDataUser [id=" + id + ", alias=" + alias + ", name=" + name + ", lastname=" + lastname + ", email="
				+ email + ", roles=" + roles + ", favourites=" + favourites.toString() + ", sex=" + sex + ", city=" + city
				+ ", country=" + country + ", phoneNumber=" + phoneNumber + ", description=" + description
				+ ", personalWeb=" + personalWeb + "]";
	}
}
