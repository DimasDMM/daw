package com.daw.themadridnews.requests;

import com.daw.themadridnews.utils.Message;

public class FormComment extends FormBase {
	
	protected String comment;
	
	
	public FormComment() {super();}
	
	public FormComment(String comment) {
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
		return "FormComment [comment=" + comment + "]";
	}
	
	public Message validation() {
		Message message = super.validation();
		
		if(message.getCode() != 0)
			return message;
		
		if(comment.isEmpty()) {
			message.setCode(100);
			message.setMessage("Te has dejado el comentario en blanco. Por favor, escriba un comentario antes de continuar.");
			
		} else if(!Validator.strValidLength(comment, 5, 500)) {
			message.setCode(101);
			message.setMessage("El comentario no es válido, la longitud debe estar entre 5 y 500 caracteres.");
		}
		
		return message;
	}
}
