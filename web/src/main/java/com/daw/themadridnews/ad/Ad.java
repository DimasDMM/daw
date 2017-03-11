package com.daw.themadridnews.ad;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="ads")
public class Ad {
	
	/*
	 * Posibles tipos de anuncios
	 * 0: Banner normal, imagen estatica en cualquier parte
	 * 1: Fondo de la web
	 * 
	 * Estado de un anuncio
	 * 0: Aun no ha empezado a ser publico
	 * 1: Activo, mostrandose a los usuarios
	 * 2: Finalizado
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;
	
	@NotNull
	protected String title;
	
	@NotNull
	protected String url;
	
	@NotNull
	protected int type;
	
	@NotNull
	protected int weight;
	
	// Limitaciones de visualizacion
	@NotNull protected boolean isLimDateStart;
	@NotNull protected boolean isLimDateEnd;
	@NotNull protected boolean isLimClicks;
	@NotNull protected boolean isLimViews;
	
	protected Date limDateStart;
	protected Date limDateEnd;
	protected int limClicks;
	protected int limViews;
	
	// Estadisticas actuales
	@NotNull protected int nClicks;
	@NotNull protected int nViews;
	
	@NotNull
	protected int status;
	
	@NotNull
	protected Date dateInsert;
	
	
	public Ad() {}

	/*
	 * Para las restricciones que no queramos, introducir NULL o -1
	 */
	public Ad(String title, String url, int type, int weight, Date limDateStart, Date limDateEnd, int limClicks, int limViews) {
		this.title = title;
		this.url = url;
		this.type = type;
		this.weight = weight;
		this.nClicks = 0;
		this.nViews = 0;
		this.dateInsert = new Date();

		this.isLimDateStart = (limDateStart != null);
		this.isLimDateEnd = (limDateEnd != null);
		this.isLimClicks = (limClicks > 0);
		this.isLimViews = (limViews > 0);
		
		this.limDateStart = limDateStart;
		this.limDateEnd = limDateEnd;
		this.limClicks = (isLimClicks ? limClicks : -1);
		this.limViews = (isLimViews ? limViews : -1);

		this.status = AdService.getStatus(this);
	}

	public long getId() {
		return id;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public boolean isLimDateStart() { return isLimDateStart; }
	public boolean isLimDateEnd() { return isLimDateEnd; }
	public boolean isLimClicks() { return isLimClicks; }
	public boolean isLimViews() { return isLimViews; }
	public int getStatus() { return status; }

	public Date getLimDateStart() { return limDateStart; }
	public Date getLimDateEnd() { return limDateEnd; }
	public int getLimClicks() { return limClicks; }
	public int getLimViews() { return limViews; }

	public void setLimDateStart(Date limDateStart) {
		this.isLimDateStart = (limDateStart != null);
		this.limDateStart = limDateStart;
		this.status = AdService.getStatus(this);
	}
	public void setLimDateEnd(Date limDateEnd) {
		this.isLimDateEnd = (limDateEnd != null);
		this.limDateEnd = limDateEnd;
		this.status = AdService.getStatus(this);
	}

	public void setLimClicks(int limClicks) {
		this.isLimClicks = (limClicks > 0);
		this.limClicks = (isLimClicks ? limClicks : -1);
		this.status = AdService.getStatus(this);
	}

	public void setLimViews(int limViews) {
		this.isLimViews = (limClicks > 0);
		this.limViews = (isLimViews ? limViews : -1);
		this.status = AdService.getStatus(this);
	}

	public int getClicks() {
		return nClicks;
	}

	public int getViews() {
		return nViews;
	}

	public Date getDateInsert() {
		return dateInsert;
	}

	@Override
	public String toString() {
		return "Ad [id=" + id + ", title=" + title + ", url=" + url + ", type=" + type + ", weight=" + weight
				+ ", isLimDateStart=" + isLimDateStart + ", isLimDateEnd=" + isLimDateEnd + ", isLimClicks="
				+ isLimClicks + ", isLimViews=" + isLimViews + ", limDateStart=" + limDateStart + ", limDateEnd="
				+ limDateEnd + ", limClicks=" + limClicks + ", limViews=" + limViews + ", nClicks=" + nClicks
				+ ", nViews=" + nViews + ", dateInsert=" + dateInsert + "]";
	}
}
