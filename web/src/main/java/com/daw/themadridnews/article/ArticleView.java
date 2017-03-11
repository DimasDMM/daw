package com.daw.themadridnews.article;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.daw.themadridnews.comment.Comment;
import com.daw.themadridnews.comment.CommentRepository;
import com.daw.themadridnews.user.User;


public class ArticleView {
	
	protected long id;
	protected Category category;
	protected String title;
	protected String titleShort;
	protected String content;
	protected String contentShort;
	protected User author;
	protected String source;
	protected List<String> tags;
	protected boolean visible;
	protected long nComments;
	protected int views;
	protected List<Comment> comments;
	protected String dateInsertStrLong;
	protected String dateInsertStrShort;
	protected Date dateInsert;
	
	
	public ArticleView() {}
	
	public ArticleView(Article article, long nComments) {
		this(article);
		this.nComments = nComments;
	}
	
	public ArticleView(Article article) {
		String categoryId = article.getCategory();
		Category category = new Category(categoryId, CategoryService.getName(categoryId));
		this.category = category;
		
		this.id = article.getId();
		this.title = article.getTitle();
		this.content = article.getContent();
		this.author = article.getAuthor();
		this.source = article.getSource();
		this.tags = article.getTags();
		this.visible = article.isVisible();
		this.views = article.getViews();
		this.comments = article.getComments();
		this.dateInsert = article.getDateInsert();

		SimpleDateFormat ftl = new SimpleDateFormat ("dd-MM-yyyy 'a las' hh:mm'h'");
		dateInsertStrLong = ftl.format(dateInsert);

		SimpleDateFormat fts = new SimpleDateFormat ("dd-MM-yyyy");
		dateInsertStrShort = fts.format(dateInsert);
		
		titleShort = title;
		if(title.length() > 20)
			titleShort = title.substring(0,20) + "...";
		
		contentShort = content.replaceAll("\\<.*?>","");
		if(contentShort.length() > 60)
			contentShort = contentShort.substring(0,60) + "...";
	}

	public long getId() {
		return id;
	}

	public Category getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	}

	public String getTitleShort() {
		return titleShort;
	}

	public String getContent() {
		return content;
	}
	
	public String getFormatedContent() {
		return MarkdownConverter.getFormatedHtml(content);
	}

	public User getAuthor() {
		return author;
	}

	public String getSource() {
		return source;
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
	
	public boolean isVisible() {
		return visible;
	}
	
	public int getViews() {
		return views;
	}
	
	public long getNumComments() {
		return nComments;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	
	public Date getDateInsert() {
		return dateInsert;
	}
	
	public String getDateInsertStrLong() {
		return dateInsertStrLong;
	}
	
	public String getDateInsertStrShort() {
		return dateInsertStrShort;
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
			c.add( new ArticleView( it.next() ) );
		
		return c;
	}
	
	public static List<ArticleView> castList(List<Article> l, CommentRepository rep) {
		List<ArticleView> c = new ArrayList<ArticleView>();
		Iterator<Article> it = l.iterator();
		
		while(it.hasNext()) {
			Article a = it.next();
			long nComments = rep.countByArticle(a);
			
			ArticleView av = new ArticleView(a, nComments);
			
			c.add( av );
		}
		
		return c;
	}
}
