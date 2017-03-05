package com.daw.themadridnews.article;

import java.util.ArrayList;
import java.util.Iterator;
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
		Pattern p;
		ArrayList<String> replace = new ArrayList<String>();
		
		// Parafos
		p = Pattern.compile("(?:([*_`\"]|\\*\\*|\\[)?[a-zA-Z]+)(.*)");
		replace.add("<p>");
		replace.add("</p>");
		cb = replaceRegex(cb, p, replace);
		
		// Letra negrita
		p = Pattern.compile("\\*\\*([^*]*)(\\*(?!\\*)[^*]*)*\\*\\*");
		replace.add("<b>");
		replace.add("</b>");
		cb = replaceRegex(cb, p, replace);

		// Imagenes laterales
		p = Pattern.compile("\\[\\[([^|]+)\\|right\\|([^\\]]+)\\]\\]");
		replace.add("<img class=\"blog-grid-img-v1\" src=\"");
		replace.add("\" alt=\"");
		replace.add("\">");
		cb = replaceRegex(cb, p, replace);

		// Imagen ancho completo
		p = Pattern.compile("\\[\\[([^|]+)\\|full\\|([^\\]]+)\\]\\]");
		replace.add("<img class=\"img-responsive margin-bottom-30\" src=\"");
		replace.add("\" alt=\"");
		replace.add("\">");
		cb = replaceRegex(cb, p, replace);
		
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
	
	
	private static StringBuilder replaceRegex(StringBuilder cb, Pattern p, ArrayList<String> replace) {
		Matcher m = p.matcher(cb);
		
		int start = 0;
		while (m.find(start)) {
			String replacement = replaceRegexAux(m, replace);
			
			cb.replace(m.start(), m.end(), replacement);
			start = m.start() + replacement.length();
		}
		
		return cb;
	}
	
	private static String replaceRegexAux(Matcher m, ArrayList<String> replace) {
		StringBuilder rb = new StringBuilder();
		int i = 1;
		
		Iterator<String> it = replace.iterator();
		while(it.hasNext()) {
			rb.append( it.next() );
			
			if(it.hasNext()) {
				rb.append( m.group(i) );
				i++;
			}
		}
		
		return rb.toString();
	}
}
