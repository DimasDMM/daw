package com.daw.themadridnews.requests;

import java.util.List;
import com.daw.themadridnews.utils.Message;

public class FormNewArticle extends FormBase {

	protected String category;
	protected String title;
	protected String content;
	protected String source;
	protected List<String> tags;
	
	
	public FormNewArticle() {super();}
	
	public FormNewArticle(String category, String title, String content, String source, List<String> tags) {
		super();
		this.category = category;
		this.title = title;
		this.content = content;
		this.source = source;
		this.tags = tags;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "FormNewArticle [category=" + category + ", title=" + title + ", content=" + content
				+ ", source=" + source + ", tags=" + tags + "]";
	}
	
	
	public Message validation() {
		Message message = super.validation();
		if(message.getCode() != 0)
			return message;
		
		if(category.isEmpty() || title.isEmpty() || content.isEmpty() || source.isEmpty() || tags.isEmpty()) {
			message.setCode(100);
			message.setMessage("Se ha dejado campos en blanco. Por favor, revise todo antes de continuar.");
			
		} else if(!Validator.strValidLength(title, 5, -1)) {
			message.setCode(101);
			message.setMessage("El titulo del articulo no es válido, es demasiado corto.");
			
		} else if(!Validator.strValidList(tags)) {
			message.setCode(102);
			message.setMessage("La lista de etiquetas no es valida.");
		}
		
		return message;
	}
}
