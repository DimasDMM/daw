package com.daw.themadridnews.ad;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.daw.themadridnews.user.User;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="ads")
public class Ad {

	public static interface Basic {}
	public static interface Details {}
	
	/*
	 * Estado de un anuncio
	 * 0: Aun no ha empezado a ser publico
	 * 1: Activo, mostrandose a los usuarios
	 * 2: Finalizado
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Basic.class)
	protected long id;
	
	@NotNull
	@OneToOne
	@JsonView(Details.class)
	protected User author;
	
	@NotNull
	@JsonView(Basic.class)
	protected String title;
	
	@NotNull
	@JsonView(Basic.class)
	protected String url;
	
	@NotNull
	@JsonView(Details.class)
	protected int weight;
	
	// Limitaciones de visualizacion
	@Column(nullable = true)
	@JsonView(Details.class)
	protected Date limDateStart;
	
	@Column(nullable = true)
	@JsonView(Details.class)
	protected Date limDateEnd;
	
	@Column(nullable = true)
	@JsonView(Details.class)
	protected Integer limClicks;
	
	@Column(nullable = true)
	@JsonView(Details.class)
	protected Integer limViews;
	
	// Estadisticas actuales
	@NotNull
	@JsonView(Details.class)
	protected int clicks;
	
	@NotNull
	@JsonView(Details.class)
	protected int views;
	
	@NotNull
	@JsonView(Details.class)
	protected Date dateInsert;
	
	
	public Ad() {}

	/*
	 * Para las restricciones que no queramos, introducir NULL o -1
	 */
	public Ad(User author, String title, String url, int weight, Date limDateStart, Date limDateEnd, Integer limClicks, Integer limViews) {
		this.author = author;
		this.title = title;
		this.url = url;
		this.weight = weight;
		this.clicks = 0;
		this.views = 0;
		
		this.limDateStart = limDateStart;
		this.limDateEnd = limDateEnd;
		this.limClicks = limClicks;
		this.limViews = limViews;

		this.dateInsert = new Date();
	}

	public long getId() {
		return id;
	}
	
	public User getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Date getLimDateStart() { return limDateStart; }
	public Date getLimDateEnd() { return limDateEnd; }
	public Integer getLimClicks() { return limClicks; }
	public Integer getLimViews() { return limViews; }

	public void setLimDateStart(Date limDateStart) {
		this.limDateStart = limDateStart;
	}
	public void setLimDateEnd(Date limDateEnd) {
		this.limDateEnd = limDateEnd;
	}

	public void setLimClicks(Integer limClicks) {
		this.limClicks = limClicks;
	}

	public void setLimViews(Integer limViews) {
		this.limViews = limViews;
	}

	public int getClicks() {
		return clicks;
	}
	
	public void addClick() {
		this.clicks++;
	}

	public int getViews() {
		return views;
	}
	
	public void addView() {
		this.views++;
	}

	public Date getDateInsert() {
		return dateInsert;
	}

	@Override
	public String toString() {
		return "Ad [id=" + id + ", author=" + author + ", title=" + title + ", url=" + url
				+ ", weight=" + weight + ", limDateStart=" + limDateStart + ", limDateEnd=" + limDateEnd
				+ ", limClicks=" + limClicks + ", limViews=" + limViews + ", clicks=" + clicks + ", views=" + views
				+ ", dateInsert=" + dateInsert + "]";
	}
}
