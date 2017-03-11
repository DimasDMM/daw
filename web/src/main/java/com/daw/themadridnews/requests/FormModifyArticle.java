package com.daw.themadridnews.requests;

import java.util.List;

import com.daw.themadridnews.utils.Message;

public class FormModifyArticle extends FormNewArticle {
	
	public FormModifyArticle() {super();}
	
	public FormModifyArticle(String category, String title, String content, String source, List<String> tags) {
		super(category, title, content, source, tags);
	}

	@Override
	public String toString() {
		return "FormModifyArticle [category=" + category + ", title=" + title + ", content=" + content
				+ ", source=" + source + ", tags=" + tags + "]";
	}
	
	public Message validation() {
		Message message = new Message();
		
		if(category.isEmpty() || title.isEmpty() || content.isEmpty() || source.isEmpty() || tags.isEmpty()) {
			message.setCode(1);
			message.setMessage("Se ha dejado campos en blanco. Por favor, revise todo antes de continuar.");
			message.setType("danger");
			
		} else if(!Validator.strValidLength(title, 5, -1)) {
			message.setCode(2);
			message.setMessage("El titulo del articulo no es válido, es demasiado corto.");
			message.setType("danger");
			
		} else if(!Validator.strValidList(tags)) {
			message.setCode(3);
			message.setMessage("La lista de etiquetas no es valida.");
			message.setType("danger");
		}
		
		return message;
	}
}
