package com.daw.themadridnews.requests;

import com.daw.themadridnews.utils.Message;

public class FormModifyAd extends FormNewAd {
		
	public FormModifyAd() {super();}

	public FormModifyAd(String title, String url, String type, String weight, String datestart, String dateend, String clicks, String views) {
		super(title, url, type, weight, datestart, dateend, clicks, views);
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
				+ ", datestart=" + datestart + ", dateend=" + dateend + ", clicks=" + clicks + ", views=" + views + "]";
	}
}
