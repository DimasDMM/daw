package com.daw.themadridtimes.article;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.daw.themadridtimes.comment.Comment;
import com.daw.themadridtimes.user.User;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="articles")
public class Article {

	public static interface Basic {}
	public static interface Details {}
	
	/*
	 * Lista de posibles categorias:
	 * - madrid
	 * - spain
	 * - world
	 * - sport
	 * - technology
	 * - culture
	 * 
	 * Lista de estados (status):
	 * - 0: oculto
	 * - 1: activo/visible
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Basic.class)
	protected long id;
	
	@NotNull
	@JsonView(Basic.class)
	protected String category;
	
	@NotNull
	@JsonView(Basic.class)
	protected String title;
	
	@NotNull
	@Column(columnDefinition="TEXT")
	@JsonView(Basic.class)
	protected String content;
	
	@ManyToOne
	@NotNull
	@JsonView(Basic.class)
	protected User author;

	@NotNull
	@JsonView(Basic.class)
	protected String source;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@NotNull
	@JsonView(Basic.class)
	protected List<String> tags;
	
	@NotNull
	@JsonView(Details.class)
	protected boolean visible;
	
	@NotNull
	@JsonView(Basic.class)
	protected int views;
	
	@OneToMany(mappedBy="article", cascade=CascadeType.ALL)
	@ElementCollection(fetch = FetchType.LAZY)
	protected List<Comment> comments;
	
	@NotNull
	@JsonView(Basic.class)
	protected Date dateInsert;
	
	
	public Article() {
		tags = new ArrayList<String>();
		comments = new ArrayList<Comment>();
		dateInsert = new Date();
	}
	
	public Article(String category, String title, String content, User author, String source, List<String> tags, List<Comment> comments, boolean visible) {
		this.category = category;
		this.title = title;
		this.content = content;
		this.author = author;
		this.source = source;
		this.tags = tags;
		this.visible = visible;
		this.views = 0;
		this.comments = comments;
		this.dateInsert = new Date();
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
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public int getViews() {
		return views;
	}
	
	public void addView() {
		this.views += 1;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public Date getDateInsert() {
		return dateInsert;
	}
	
	public void setDateInsert(Date dateInsert) {
		this.dateInsert = dateInsert;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", category=" + category + ", title=" + title + ", content=" + content
				+ ", author=" + author + ", source=" + source + ", tags=" + tags + "]";
	}
}
