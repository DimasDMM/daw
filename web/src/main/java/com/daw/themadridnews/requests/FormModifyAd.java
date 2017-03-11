package com.daw.themadridnews.requests;

import com.daw.themadridnews.utils.Message;

public class FormModifyAd extends FormNewAd {
		
	public FormModifyAd() {super();}

	public FormModifyAd(String title, String url, int type, int weight, String dateStart, String dateEnd, int clicks, int views) {
		super(title, url, type, weight, dateStart, dateEnd, clicks, views);
	}

	public Message validation() {
		Message message = super.validation();
		
		if(message.getCode() != 0)
			return message;
		
		return message;
	}

	@Override
	public String toString() {
		return "FormModifyAd [title=" + title + ", url=" + url + ", type=" + type + ", weight=" + weight
				+ ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + ", clicks=" + clicks + ", views=" + views + "]";
	}
}
