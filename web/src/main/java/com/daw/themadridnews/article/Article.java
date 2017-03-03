package com.daw.themadridnews.article;

import java.util.List;
import javax.persistence.*;
import com.daw.themadridnews.user_model.User;

@Entity
@Table(name="articles")
public class Article {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;
	
	protected int category;
	protected String title;
	protected String content;
	
	@ManyToOne
	protected User author;
	
	protected String source;
	protected String tags;
	
	public Article() {}
	
	public Article(long id, int category, String title, String content, User author, String source, String[] tags) {
		this.id = id;
		this.category = category;
		this.title = title;
		this.content = content;
		this.author = author;
		this.source = source;
		
		this.setTags(tags);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
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

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String[] getTags() {
		return tags.split(",");
	}

	public void setTags(String[] tags) {
		StringBuffer tagsBuff = new StringBuffer();
		for (int i = 0; i < tags.length; i++) {
			tagsBuff.append( tags[i] );
			tagsBuff.append(",");
		}
		this.tags = tagsBuff.toString();
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", category=" + category + ", title=" + title + ", content=" + content
				+ ", author=" + author + ", source=" + source + ", tags=" + tags + "]";
	}
}
