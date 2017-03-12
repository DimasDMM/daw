package com.daw.themadridnews.user;

import com.daw.themadridnews.favourite.Favourite;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String name;

	@NotNull
	private String lastName;

	@Column(unique = true)
	@NotNull
	private String email;

	@NotNull
	private String passwordHash;

	@ElementCollection(fetch = FetchType.EAGER)
	@NotNull
	private List<String> roles;

	@Column(unique = true)
	private String alias;

	@OneToOne(cascade = CascadeType.ALL)
	private Favourite favourites;

	private char sex;
	private String city;
	private String country;
	private String phoneNumber;
	private String description;
	private String personalWeb;

	// Constructors
	public User() {
		this.roles = new ArrayList();
	}

	public User(String name, String lastName, String email, String pass, String... roles) {
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.passwordHash = new BCryptPasswordEncoder().encode(pass);
		this.roles = new ArrayList<>(Arrays.asList(roles));
	}

	// Method toString
	@Override
	public String toString() {
		return "User [name=" + name + ", lastName=" + lastName + ", alias=" + alias + "]";
	}

	// Methods Getters & Setters
	public long getId() {
		return this.id;
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

	public String getPasswordHash() {
		return passwordHash;
	}

	public List<String> getRoles() {
		return roles;
	}

	public String getAlias() {
		return alias;
	}

	public Favourite getFavourites() {
		return this.favourites;
	}

	public char getSex() {
		return sex;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getDescription() {
		return description;
	}

	public String getPersonalWeb() {
		return personalWeb;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = new BCryptPasswordEncoder().encode(passwordHash);
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public void setFavourites(Favourite favourites) {
		this.favourites = favourites;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPersonalWeb(String personalWeb) {
		this.personalWeb = personalWeb;
	}

}
