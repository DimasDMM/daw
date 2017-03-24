package com.daw.themadridnews.requests;

import com.daw.themadridnews.utils.Message;

public class ApiComment implements ApiBase {
	
	protected String comment;
	
	
	public ApiComment() {super();}
	
	public ApiComment(String comment) {
		this.comment = comment;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "ApiComment [comment=" + comment + "]";
	}
	
	public Message validation() {
		Message message = new Message();
		
		if(comment.isEmpty()) {
			message.setCode(100);
			message.setMessageTxt("Te has dejado el comentario en blanco. Por favor, escriba.");
			message.setType("danger");
			
		} else if(!Validator.strValidLength(comment, 5, 500)) {
			message.setCode(101);
			message.setMessageTxt("El comentario no es válido, la longitud debe estar entre 5 y 500 caracteres.");
			message.setType("danger");
		}
		
		return message;
	}
}
