package com.daw.themadridnews.article;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.daw.themadridnews.comment.Comment;
import com.daw.themadridnews.user.User;


public class ArticleView {
	
	protected long id;
	protected Category category;
	protected String title;
	protected String titleShort;
	protected String content;
	protected User author;
	protected String source;
	protected List<String> tags;
	protected boolean visible;
	protected int views;
	protected List<Comment> comments;
	protected Date dateInsert;
	
	
	public ArticleView() {}
	
	public ArticleView(Article article) {
		String categoryId = article.getCategory();
		Category category = new Category(categoryId, CategoryService.getName(categoryId));
		this.category = category;
		
		this.title = article.getTitle();
		this.content = article.getContent();
		this.author = article.getAuthor();
		this.source = article.getSource();
		this.tags = article.getTags();
		this.visible = article.isVisible();
		this.views = article.getViews();
		this.comments = article.getComments();
		this.dateInsert = article.getDateInsert();
		
		if(title.length() > 20)
			titleShort = title.substring(0,20) + "...";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public String getTitleShort() {
		return titleShort;
	}

	public void setTitle(String title) {
		this.title = title;

		if(title.length() > 20)
			titleShort = title.substring(0,20) + "...";
	}

	public String getContent() {
		return content;
	}
	
	public String getFormatedContent() {
		return MarkdownConverter.getFormatedHtml(content);
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
	
	public String getTagsStr() {
		StringBuilder sb = new StringBuilder();
		Iterator<String> it = tags.iterator();
		
		while(it.hasNext()) {
			sb.append( it.next() );
			
			if(it.hasNext())
				sb.append(",");
		}
		
		return sb.toString();
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
	
	public String getStrDateInsert() {
		SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy 'a las' hh:mm'h'");
		return ft.format(dateInsert);
	}
	
	public void setDateInsert(Date dateInsert) {
		this.dateInsert = dateInsert;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", category=" + category + ", title=" + title + ", content=" + content
				+ ", author=" + author + ", source=" + source + ", tags=" + tags + "]";
	}
	
	public static List<ArticleView> castList(List<Article> l) {
		List<ArticleView> c = new ArrayList<ArticleView>();
		Iterator<Article> it = l.iterator();
		
		while(it.hasNext())
			c.add( new ArticleView(it.next()) );
		
		return c;
	}
}
