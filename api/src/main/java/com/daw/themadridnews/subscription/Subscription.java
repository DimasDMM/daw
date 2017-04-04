package com.daw.themadridnews.subscription;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="subscriptions")
public class Subscription {
	
	public static interface Basic {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@JsonView(Basic.class)
	@Column(unique = true)
	@NotNull
	private String email;
	
	
	public Subscription() {}

	public Subscription(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Subscription [id=" + id + ", email=" + email + "]";
	}
}
