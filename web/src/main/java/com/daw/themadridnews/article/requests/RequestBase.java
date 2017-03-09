package com.daw.themadridnews.article.requests;

import com.daw.themadridnews.utils.Message;

public class RequestBase {
	protected String _csrf;
	
	public String getCsrf() {
		return _csrf;
	}
	
	public Message validation() {
		return new Message();
	}
}
