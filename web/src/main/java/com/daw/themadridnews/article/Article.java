package com.daw.themadridnews.article;

import java.util.List;
import javax.persistence.*;
import com.daw.themadridnews.user_model.User;

@Entity
@Table(name="articles")
public class Article {
	
	/*
	 * Lista de posibles categorias:
	 * - madrid
	 * - spain
	 * - world
	 * - sport
	 * - technology
	 * - culture
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;
	
	protected String category;
	protected String title;
	protected String content;
	
	@ManyToOne
	protected User author;
	
	protected String source;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> tags;
	
	public Article() {}
	
	public Article(String category, String title, String content, User author, String source, List<String> tags) {
		this.category = category;
		this.title = title;
		this.content = content;
		this.author = author;
		this.source = source;
		this.tags = tags;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", category=" + category + ", title=" + title + ", content=" + content
				+ ", author=" + author + ", source=" + source + ", tags=" + tags + "]";
	}
}
