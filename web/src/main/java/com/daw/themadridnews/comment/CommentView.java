package com.daw.themadridnews.comment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.daw.themadridnews.article.ArticleView;
import com.daw.themadridnews.user.User;

public class CommentView {
	
	protected long id;
	protected ArticleView article;
	protected User author;
	protected long number;
	protected String comment;
	protected String commentShort;
	protected Date dateInsert;
	protected String dateInsertStr;
	
	
	public CommentView() {}

	public CommentView(Comment comment) {
		this.article = new ArticleView( comment.getArticle() );
		this.author = comment.getAuthor();
		this.number = comment.getNumber();
		this.comment = comment.getComment();
		this.dateInsert = comment.getDateInsert();

		SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy 'a las' hh:mm'h'");
		this.dateInsertStr = ft.format(comment.getDateInsert());
		
		commentShort = this.comment;
		if(this.comment.length() > 100)
			commentShort = this.comment.substring(0,100) + "...";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ArticleView getArticle() {
		return article;
	}

	public void setArticle(ArticleView article) {
		this.article = article;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
	
	public long getNumber() {
		return number;
	}
	
	public void setNumber(long number) {
		this.number = number;
	}

	public String getComment() {
		return comment;
	}

	public String getCommentShort() {
		return commentShort;
	}

	public void setComment(String comment) {
		this.comment = comment;

		commentShort = this.comment;
		if(this.comment.length() > 100)
			commentShort = this.comment.substring(0,100) + "...";
	}

	public String getDateInsertStr() {
		return dateInsertStr;
	}

	public void setDateInsert(Date dateInsert) {
		this.dateInsert = dateInsert;
		
		SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy 'a las' hh:mm");
		this.dateInsertStr = ft.format(dateInsert);
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", author=" + author + ", number="+ number +", comment=" + comment + ", dateInsert=" + dateInsert + "]";
	}
	
	public static List<CommentView> castList(List<Comment> l) {
		List<CommentView> c = new ArrayList<CommentView>();
		Iterator<Comment> it = l.iterator();
		
		while(it.hasNext())
			c.add( new CommentView(it.next()) );
		
		return c;
	}
}
