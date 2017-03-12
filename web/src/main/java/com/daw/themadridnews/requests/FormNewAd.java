package com.daw.themadridnews.requests;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.daw.themadridnews.utils.Message;

public class FormNewAd extends RequestBase {
	
	protected String title;
	protected String url;
	protected String type;
	protected String weight;
	protected String datestart;
	protected String dateend;
	protected String clicks;
	protected String views;
	
	
	public FormNewAd() {super();}

	public FormNewAd(String title, String url, String type, String weight, String datestart, String dateend, String clicks, String views) {
		super();
		this.title = title;
		this.url = url;
		this.type = type;
		this.weight = weight;
		this.datestart = datestart;
		this.dateend = dateend;
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
		if(type == null || type.isEmpty()) return 0;
		
		return Integer.valueOf( type );
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getWeight() {
		if(weight == null || weight.isEmpty()) return 0;
		
		return Integer.valueOf( weight );
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Date getDatestart() {
		if(datestart == null || datestart.isEmpty())
			return null;
		
		try {
			SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
			return ft.parse( this.datestart );
		} catch (Exception e) {
			return null;
		}
	}

	public void setDatestart(String datestart) {
		this.datestart = datestart;
	}

	public Date getDateend() {
		if(dateend == null || dateend.isEmpty())
			return null;
		
		try {
			SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
			return ft.parse( this.dateend );
		} catch (Exception e) {
			return null;
		}
	}

	public void setDateend(String dateend) {
		this.dateend = dateend;
	}

	public int getClicks() {
		if(clicks == null || clicks.isEmpty()) return 0;
		
		return Integer.valueOf( clicks );
	}

	public void setClicks(String clicks) {
		this.clicks = clicks;
	}

	public int getViews() {
		if(views == null || views.isEmpty()) return 0;
		
		return Integer.valueOf( views );
	}

	public void setViews(String views) {
		this.views = views;
	}


	public Message validation() {
		Message message = new Message();
		
		if(title.isEmpty() || url.isEmpty()) {
			message.setCode(1);
			message.setMessage("Se ha dejado campos en blanco. Por favor, revise todo antes de continuar.");
			message.setType("danger");
			
		} else if(
				!Validator.intValidMinMax(this.getType(), 0, 1) ||
				!Validator.intValidMin(this.getWeight(), 1) ||
				!Validator.intValidMin(this.getClicks(), 0) ||
				!Validator.intValidMin(this.getViews(), 0)
		) {
			message.setCode(2);
			message.setMessage("Hay campos con informacion no valida. Por favor, reviselos antes de continuar");
			message.setType("danger");	
		}
		
		return message;
	}

	@Override
	public String toString() {
		return "FormNewAd [title=" + title + ", url=" + url + ", type=" + type + ", weight=" + weight + ", datestart="
				+ datestart + ", dateend=" + dateend + ", clicks=" + clicks + ", views=" + views + "]";
	}
}
