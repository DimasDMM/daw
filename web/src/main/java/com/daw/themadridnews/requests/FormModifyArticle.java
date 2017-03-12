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
		Message message = super.validation();
		return message;
	}
}
