package com.daw.themadridnews.article.requests;

import java.util.List;
import com.daw.themadridnews.extensions.RequestBase;
import com.daw.themadridnews.user.User;

public class FormModifyArticle extends FormNewArticle {
	
	protected long id;
	protected String category;
	protected String title;
	protected String content;
	protected String source;
	protected List<String> tags;
	
	
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
		return "FormNewArticle [id=" + id + ", category=" + category + ", title=" + title + ", content=" + content
				+ ", source=" + source + ", tags=" + tags + "]";
	}
}
