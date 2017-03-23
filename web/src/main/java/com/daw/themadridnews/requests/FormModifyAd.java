package com.daw.themadridnews.requests;

import com.daw.themadridnews.utils.Message;

public class FormModifyAd extends FormNewAd {
		
	public FormModifyAd() {super();}

	public FormModifyAd(String title, String url, int weight, String limdatestart, String limdateend, Integer limclicks, Integer limviews) {
		super(title, url, weight, limdatestart, limdateend, limclicks, limviews);
	}

	public Message validation() {
		Message message = super.validation();
		
		return message;
	}

	@Override
	public String toString() {
		return "FormModifyAd [title=" + title + ", url=" + url + ", weight=" + weight + ", limdatestart=" + limdatestart
				+ ", limdateend=" + limdateend + ", limclicks=" + limclicks + ", limviews=" + limviews + "]";
	}
}
