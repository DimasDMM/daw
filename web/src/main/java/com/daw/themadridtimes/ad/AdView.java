package com.daw.themadridtimes.ad;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class AdView {
	
	protected Ad ad;
	
	protected long id;
	protected String title;
	protected String url;
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
	protected int clicks;
	protected int views;

	protected boolean statusWaiting;
	protected boolean statusActive;
	protected boolean statusOver;
	
	protected Date dateInsert;
	
	
	public AdView() {}

	public AdView(Ad ad) {
		this.ad = ad;
		
		this.id = ad.getId();
		this.title = ad.getTitle();
		this.url = ad.getUrl();
		this.weight = ad.getWeight();
		this.clicks = ad.getClicks();
		this.views = ad.getViews();
		this.dateInsert = ad.getDateInsert();

		this.isLimDateStart = ad.getLimDateStart()!=null;
		this.isLimDateEnd = ad.getLimDateEnd()!=null;
		this.isLimClicks = ad.getLimClicks()!=null;
		this.isLimViews = ad.getLimViews()!=null;

		SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
		this.limDateStart = ( this.isLimDateStart ? ft.format( ad.getLimDateStart() ) : "&infin;" );
		this.limDateEnd = ( this.isLimDateEnd ? ft.format( ad.getLimDateEnd() ) : "&infin;" );
		
		this.limClicks = ( this.isLimClicks ? String.valueOf( ad.getLimClicks() ) : "&infin;" );
		this.limViews = ( this.isLimViews ? String.valueOf( ad.getLimViews() ) : "&infin;" );

		this.statusWaiting = (AdCommons.getStatus(ad) == 0);
		this.statusActive = (AdCommons.getStatus(ad) == 1);
		this.statusOver = (AdCommons.getStatus(ad) == 2);
	}
	
	public Ad getAd() {
		return ad;
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

	public String getLimDateStartStr() {
		SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
		return ( this.isLimDateStart ? ft.format( this.ad.getLimDateStart() ) : "" );
	}
	public String getLimDateEndStr() {
		SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
		return ( this.isLimDateEnd ? ft.format( this.ad.getLimDateEnd() ) : "" );
	}
	public String getLimClicksStr() { return ( this.isLimClicks ? String.valueOf( this.ad.getLimClicks() ) : "" ); }
	public String getLimViewsStr() { return ( this.isLimViews ? String.valueOf( this.ad.getLimViews() ) : "" ); }

	public int getClicks() {
		return clicks;
	}

	public int getViews() {
		return views;
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
		return "Ad [id=" + id + ", title=" + title + ", url=" + url + ", weight=" + weight
				+ ", isLimDateStart=" + isLimDateStart + ", isLimDateEnd=" + isLimDateEnd + ", isLimClicks="
				+ isLimClicks + ", isLimViews=" + isLimViews + ", limDateStart=" + limDateStart + ", limDateEnd="
				+ limDateEnd + ", limClicks=" + limClicks + ", limViews=" + limViews + ", clicks=" + clicks
				+ ", views=" + views + ", dateInsert=" + dateInsert + "]";
	}
	
	public static List<AdView> castList(List<Ad> l) {
		List<AdView> c = new ArrayList<AdView>();
		Iterator<Ad> it = l.iterator();
		
		while(it.hasNext())
			c.add( new AdView(it.next()) );
		
		return c;
	}
}
