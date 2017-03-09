package com.daw.themadridnews.article;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="categories")
public class ArticleCategory {

	@Id
	protected String id;

	@NotNull
	protected String name;
	
	// Esta variable no es guardada en base de datos y es usada par propositos varios:
	// marcar un input select, categoria activa, etc
	protected transient boolean isActive;
	
	
	public ArticleCategory() {}

	public ArticleCategory(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
}

