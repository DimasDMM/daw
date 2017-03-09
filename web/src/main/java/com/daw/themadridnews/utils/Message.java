package com.daw.themadridnews.utils;

/*
 * IMPORTANTE:
 * Establecer primero el codigo del mensaje, antes que el propio mensaje
 * Asi se añadira "<b>Error: </b>" o "<b>OK: </b>" al inicio dependiendo del valor del codigo
 */
public class Message {
	protected int code;
	protected String message;
	protected String type;
	
	public Message() {}
	
	public Message(int code, String message, String type) {
		this.code = code;
		setMessage(message);
		this.type = type;
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		StringBuilder sb = new StringBuilder();
		
		if(code == 0) {
			sb.append("<b>OK: </b>");
		} else {
			sb.append("<b>Error: </b>");
		}
		
		sb.append(message);
		this.message = sb.toString();
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
}
