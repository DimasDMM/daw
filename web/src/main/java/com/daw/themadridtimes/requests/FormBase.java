package com.daw.themadridtimes.requests;

import com.daw.themadridtimes.utils.Message;

public abstract class FormBase {
	protected String _csrf;
	
	public String get_csrf() {
		return _csrf;
	}

	public void set_csrf(String _csrf) {
		this._csrf = _csrf;
	}

	public Message validation() {
		return new Message();
	}

	@Override
	public String toString() {
		return "RequestBase [_csrf=" + _csrf + "]";
	}
	
	
}
