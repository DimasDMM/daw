package com.daw.themadridnews.requests;

import java.util.List;
import com.daw.themadridnews.utils.Message;

public class ApiArticle implements ApiBase {

	protected Long id;
	protected String category;
	protected String title;
	protected String content;
	protected String source;
	protected boolean visible;
	protected List<String> tags;
	
	
	public ApiArticle() {super();}
	
	public ApiArticle(Long id, String category, String title, String content, String source, boolean visible, List<String> tags) {
		super();
		this.id = id;
		this.category = category;
		this.title = title;
		this.content = content;
		this.source = source;
		this.visible = visible;
		this.tags = tags;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public boolean getVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "ApiArticle [category=" + category + ", title=" + title + ", content=" + content
				+ ", source=" + source + ", visible=" + visible + ", tags=" + tags + "]";
	}
	
	
	public Message validation() {
		Message message = new Message();
		
		if(
				category == null || category.isEmpty() ||
				title == null || title.isEmpty() ||
				content == null || content.isEmpty() ||
				source == null || source.isEmpty() ||
				tags == null || tags.isEmpty()
		) {
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
