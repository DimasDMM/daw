package com.daw.themadridnews.user;

//import com.daw.themadridnews.comment.Comment;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	@Column(unique=true)
    @NotNull
    private String email;
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String passwordHash;
    
    //@OneToMany(cascade = CascadeType.ALL)
	//private List<Comment> comments = new ArrayList<>();
	@NotNull
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;

	@Column(unique=true)
	private String alias;
    char sex;
    private String city;
    private String country;
    private String phoneNumber;
    private String description;
    private String personalWeb;
    
	//Constructors
    public User(){}

	public User(String name, String lastName, String email, String pass, String... roles) {
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.passwordHash = new BCryptPasswordEncoder().encode(pass);
		this.roles = new ArrayList<>(Arrays.asList(roles));
	}
        
	public User(String name, String lastName, String email, String passwordHash){
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.passwordHash = passwordHash;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", lastName=" + lastName + ", alias=" + alias + "]";
	}

	//getters and setters

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
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


}
