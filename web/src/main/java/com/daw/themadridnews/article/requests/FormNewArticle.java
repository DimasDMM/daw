package com.daw.themadridnews.article.requests;

import java.util.List;
import com.daw.themadridnews.extensions.RequestBase;

public class FormNewArticle extends RequestBase {
	
	protected String categoryId;
	protected String title;
	protected String content;
	protected String source;
	protected List<String> tags;
	
	
	public FormNewArticle() {}
	
	public FormNewArticle(String categoryId, String title, String content, String source, List<String> tags) {
		super();
		this.categoryId = categoryId;
		this.title = title;
		this.content = content;
		this.source = source;
		this.tags = tags;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
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
		return "FormNewArticle [category=" + categoryId + ", title=" + title + ", content=" + content
				+ ", source=" + source + ", tags=" + tags + "]";
	}
}
