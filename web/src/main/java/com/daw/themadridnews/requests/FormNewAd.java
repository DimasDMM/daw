package com.daw.themadridnews.requests;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.daw.themadridnews.utils.Message;

public class FormNewAd extends FormBase {
	
	protected String title;
	protected String url;
	protected int weight;
	protected String limdatestart;
	protected String limdateend;
	protected Integer limclicks;
	protected Integer limviews;
	
	
	public FormNewAd() {super();}

	public FormNewAd(String title, String url, int weight, String limdatestart, String limdateend, Integer limclicks, Integer limviews) {
		super();
		this.title = title;
		this.url = url;
		this.weight = weight;
		this.limdatestart = limdatestart;
		this.limdateend = limdateend;
		this.limclicks = limclicks;
		this.limviews = limviews;
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

	public Date getLimdatestart() {
		if(limdatestart == null || limdatestart.isEmpty())
			return null;
		
		try {
			SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
			return ft.parse( this.limdatestart );
		} catch (Exception e) {
			return null;
		}
	}

	public void setLimdatestart(String limdatestart) {
		this.limdatestart = limdatestart;
	}

	public Date getLimdateend() {
		if(limdateend == null || limdateend.isEmpty())
			return null;
		
		try {
			SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
			return ft.parse( this.limdateend );
		} catch (Exception e) {
			return null;
		}
	}

	public void setLimdateend(String limdateend) {
		this.limdateend = limdateend;
	}

	public int getLimclicks() {
		if(limclicks == null) return 0;
		
		return Integer.valueOf( limclicks );
	}

	public void setLimclicks(Integer limclicks) {
		this.limclicks = limclicks;
	}

	public int getLimviews() {
		if(limviews == null) return 0;
		
		return Integer.valueOf( limviews );
	}

	public void setLimviews(Integer limviews) {
		this.limviews = limviews;
	}


	public Message validation() {
		Message message = super.validation();
		if(message.getCode() != 0)
			return message;
		
		if(title.isEmpty() || url.isEmpty()) {
			message.setCode(100);
			message.setMessageHtml("Se ha dejado campos en blanco. Por favor, revise todo antes de continuar.");
			message.setType("danger");
			
		} else if(
				!Validator.intValidMin(this.getWeight(), 1) ||
				!Validator.intValidMin(this.getLimclicks(), 0) ||
				!Validator.intValidMin(this.getLimviews(), 0)
		) {
			message.setCode(101);
			message.setMessageHtml("Hay campos con informacion no valida. Por favor, reviselos antes de continuar");
			message.setType("danger");	
		}
		
		return message;
	}

	@Override
	public String toString() {
		return "FormNewAd [title=" + title + ", url=" + url + ", weight=" + weight + ", limdatestart="
				+ limdatestart + ", limdateend=" + limdateend + ", limclicks=" + limclicks + ", limviews=" + limviews + "]";
	}
}
