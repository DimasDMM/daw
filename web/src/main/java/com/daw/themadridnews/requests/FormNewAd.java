package com.daw.themadridnews.requests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.daw.themadridnews.utils.Message;

public class FormNewAd extends RequestBase {
	
	protected String title;
	protected String url;
	protected int type;
	protected int weight;
	protected String dateStart;
	protected String dateEnd;
	protected int clicks;
	protected int views;
	
	
	public FormNewAd() {super();}

	public FormNewAd(String title, String url, int type, int weight, String dateStart, String dateEnd, int clicks, int views) {
		super();
		this.title = title;
		this.url = url;
		this.type = type;
		this.weight = weight;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.clicks = clicks;
		this.views = views;
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

	public Date getDateStart() {
		try {
			SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
			return ft.parse( this.dateStart );
		} catch (ParseException e) {
			return null;
		}
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		try {
			SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
			return ft.parse( this.dateEnd );
		} catch (ParseException e) {
			return null;
		}
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public int getClicks() {
		return clicks;
	}

	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}


	public Message validation() {
		Message message = new Message();
		
		if(title.isEmpty() || url.isEmpty()) {
			message.setCode(1);
			message.setMessage("Se ha dejado campos en blanco. Por favor, revise todo antes de continuar.");
			message.setType("danger");
			
		} else if(Validator.intValidMinMax(type, 0, 1)) {
			message.setCode(2);
			message.setMessage("Hay campos con informacion no valida. Por favor, reviselos antes de continuar");
			message.setType("danger");
			
		} else if(type != 0 && type != 1) {
			message.setCode(3);
			message.setMessage("El tipo de anuncio no es válido. Por favor, escoja un tipo antes de continuar.");
			message.setType("danger");
			
		} else if(type != 0 && type != 1) {
			message.setCode(3);
			message.setMessage("El tipo de anuncio no es válido. Por favor, escoja un tipo antes de continuar.");
			message.setType("danger");
		}
		
		return message;
	}

	@Override
	public String toString() {
		return "FormNewAd [title=" + title + ", url=" + url + ", type=" + type + ", weight=" + weight + ", dateStart="
				+ dateStart + ", dateEnd=" + dateEnd + ", clicks=" + clicks + ", views=" + views + "]";
	}
}
