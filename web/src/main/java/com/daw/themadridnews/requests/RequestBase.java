package com.daw.themadridnews.requests;

import com.daw.themadridnews.utils.Message;

public class RequestBase {
	protected String _csrf;
	
	public String getCsrf() {
		return _csrf;
	}
	
	public void setCsrf(String _csrf) {
		this._csrf = _csrf;
	}
	
	public Message validation() {
		return new Message();
	}
}
