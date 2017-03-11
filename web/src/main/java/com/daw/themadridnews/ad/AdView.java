package com.daw.themadridnews.ad;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class AdView {
	
	protected long id;
	protected String title;
	protected String url;
	protected int type;
	protected int weight;
	
	// Limitaciones de visualizacion
	protected boolean isLimDateStart;
	protected boolean isLimDateEnd;
	protected boolean isLimClicks;
	protected boolean isLimViews;
	
	protected String limDateStart;
	protected String limDateEnd;
	protected String limClicks;
	protected String limViews;
	
	// Estadisticas actuales
	protected int nClicks;
	protected int nViews;

	protected boolean statusWaiting;
	protected boolean statusActive;
	protected boolean statusOver;
	
	protected Date dateInsert;
	
	
	public AdView() {}

	public AdView(Ad ad) {
		this.title = ad.getTitle();
		this.url = ad.getUrl();
		this.type = ad.getType();
		this.weight = ad.getWeight();
		this.nClicks = ad.getClicks();
		this.nViews = ad.getViews();
		this.dateInsert = ad.getDateInsert();

		this.isLimDateStart = ad.isLimDateStart();
		this.isLimDateEnd = ad.isLimDateEnd();
		this.isLimClicks = ad.isLimClicks();
		this.isLimViews = ad.isLimViews();

		SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
		this.limDateStart = ( this.isLimDateStart ? ft.format( ad.getLimDateStart() ) : "&infin;" );
		this.limDateEnd = ( this.isLimDateEnd ? ft.format( ad.getLimDateEnd() ) : "&infin;" );
		
		this.limClicks = ( this.isLimClicks ? String.valueOf( ad.getLimClicks() ) : "&infin;" );
		this.limViews = ( this.isLimViews ? String.valueOf( ad.getLimViews() ) : "&infin;" );

		this.statusWaiting = (ad.getStatus() == 0);
		this.statusActive = (ad.getStatus() == 1);
		this.statusOver = (ad.getStatus() == 2);
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	public int getType() {
		return type;
	}

	public int getWeight() {
		return weight;
	}

	public boolean isLimDateStart() { return isLimDateStart; }
	public boolean isLimDateEnd() { return isLimDateEnd; }
	public boolean isLimClicks() { return isLimClicks; }
	public boolean isLimViews() { return isLimViews; }

	public String getLimDateStart() { return limDateStart; }
	public String getLimDateEnd() { return limDateEnd; }
	public String getLimClicks() { return limClicks; }
	public String getLimViews() { return limViews; }

	public int getClicks() {
		return nClicks;
	}

	public int getViews() {
		return nViews;
	}

	public Date getDateInsert() {
		return dateInsert;
	}
	
	public String getStrDateInsert() {
		SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy 'a las' hh:mm'h'");
		return ft.format(dateInsert);
	}

	@Override
	public String toString() {
		return "Ad [id=" + id + ", title=" + title + ", url=" + url + ", type=" + type + ", weight=" + weight
				+ ", isLimDateStart=" + isLimDateStart + ", isLimDateEnd=" + isLimDateEnd + ", isLimClicks="
				+ isLimClicks + ", isLimViews=" + isLimViews + ", limDateStart=" + limDateStart + ", limDateEnd="
				+ limDateEnd + ", limClicks=" + limClicks + ", limViews=" + limViews + ", nClicks=" + nClicks
				+ ", nViews=" + nViews + ", dateInsert=" + dateInsert + "]";
	}
	
	public static List<AdView> castList(List<Ad> l) {
		List<AdView> c = new ArrayList<AdView>();
		Iterator<Ad> it = l.iterator();
		
		while(it.hasNext())
			c.add( new AdView(it.next()) );
		
		return c;
	}
}
