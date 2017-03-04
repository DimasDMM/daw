package com.daw.themadridnews.article;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
	
	@NotNull
	protected String category;
	@NotNull
	protected String title;
	@NotNull
	@Column(columnDefinition="TEXT")
	protected String content;
	
	@ManyToOne
	@NotNull
	protected User author;
	
	protected String source;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@NotNull
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
	
	public String getFormatedContent() {
		StringBuilder cb = new StringBuilder( content );
		
		Pattern p = Pattern.compile("\\*\\*([^*]*)(\\*(?!\\*)[^*]*)*\\*\\*");
		Matcher m = p.matcher(cb);
		
		int start = 0;
		while (m.find(start)) {
			String replacement = "<b>"+m.group(1)+"</b>";
			
			cb.replace(m.start(), m.end(), replacement);
			start = m.start() + replacement.length();
		}
		
		return cb.toString();
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
