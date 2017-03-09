package com.daw.themadridnews.article.requests;

import java.util.List;
import com.daw.themadridnews.utils.Message;

public class FormNewArticle extends RequestBase {

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
