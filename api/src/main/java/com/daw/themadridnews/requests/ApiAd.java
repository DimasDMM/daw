package com.daw.themadridnews.requests;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.daw.themadridnews.utils.Message;

public class ApiAd implements ApiBase {
	
	/*
	 * El formato para las fechas es: YYYY-DD-MM
	 */
	protected Long id;
	protected String title;
	protected String url;
	protected int weight;
	protected String limDateStart;
	protected String limDateEnd;
	protected Integer limClicks;
	protected Integer limViews;
	
	
	public ApiAd() {
		super();
	}
	
	public ApiAd(Long id, String title, String url, int weight, String limDateStart, String limDateEnd,
			Integer limClicks, Integer limViews) {
		super();
		this.title = title;
		this.url = url;
		this.weight = weight;
		this.limDateStart = limDateStart;
		this.limDateEnd = limDateEnd;
		this.limClicks = limClicks;
		this.limViews = limViews;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getLimDateStart() {
		if(limDateStart == null || limDateStart.isEmpty())
			return null;
		
		try {
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
			return ft.parse( this.limDateStart );
		} catch (Exception e) {
			return null;
		}
	}
	
	public void setLimDateStart(String limDateStart) {
		this.limDateStart = limDateStart;
	}

	public Date getLimDateEnd() {
		if(limDateEnd == null || limDateEnd.isEmpty())
			return null;
		
		try {
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
			return ft.parse( this.limDateEnd );
		} catch (Exception e) {
			return null;
		}
	}

	public void setLimDateEnd(String limDateEnd) {
		this.limDateEnd = limDateEnd;
	}

	public Integer getLimClicks() {
		return limClicks;
	}

	public void setLimClicks(Integer limClicks) {
		this.limClicks = limClicks;
	}

	public Integer getLimViews() {
		return limViews;
	}

	public void setViews(Integer limViews) {
		this.limViews = limViews;
	}

	public Message validation() {
		Message message = new Message();
		
		if(title.isEmpty() || url.isEmpty()) {
			message.setCode(100);
			message.setMessageTxt("Se ha dejado campos en blanco. Por favor, revise todo antes de continuar.");
			message.setType("danger");
			
		} else if(
				!Validator.intValidMin(this.getWeight(), 1) ||
				!Validator.intValidMin(this.getLimClicks(), 0) ||
				!Validator.intValidMin(this.getLimViews(), 0)
		) {
			message.setCode(101);
			message.setMessageTxt("Hay campos con informacion no valida. Por favor, reviselos antes de continuar");
			message.setType("danger");	
		}
		
		return message;
	}
}
