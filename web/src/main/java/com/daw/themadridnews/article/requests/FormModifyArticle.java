package com.daw.themadridnews.article.requests;

import java.util.List;

public class FormModifyArticle extends FormNewArticle {
	
	protected long id;
	
	
	public FormModifyArticle() {}
	
	public FormModifyArticle(long id, String category, String title, String content, String source, List<String> tags) {
		super(category, title, content, source, tags);
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "FormNewArticle [id=" + id + ", category=" + categoryId + ", title=" + title + ", content=" + content
				+ ", source=" + source + ", tags=" + tags + "]";
	}
}
