package com.daw.themadridnews.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.daw.themadridnews.comment.Comment;
import com.daw.themadridnews.comment.CommentRepository;
import com.daw.themadridnews.requests.FormComment;
import com.daw.themadridnews.user.User;
import com.daw.themadridnews.user.UserComponent;
import com.daw.themadridnews.utils.Message;

@Service
public class ArticleService {

	@Autowired
	protected ArticleRepository articleRepository;

	@Autowired
	protected CommentRepository commentRepository;
	
	@Autowired
	protected UserComponent userComponent;
	
	
	// Añadir visualizacion al contador
	public void addViewToArticle(Article a) {
		if(a == null) return;
		
		a.addView();
		articleRepository.save(a);
	}

	// Obtener datos de un articulo
	public Article getArticle(long id) {
		Article a = articleRepository.findOne(id);
		
		if(a == null) return null;
		
		return a;
	}

	// Añadir comentario a un articulo
	public Message addComment(long id, FormComment r) {
		Message message;
		Article article = articleRepository.findOne(id);
		
		if(article == null) {
			message = new Message(1, "No se ha podido localizar el articulo dentro de la base de datos.", "danger");
			return message;
		}
		
		// Validacion
		message = r.validation();
		if(message.getCode() != 0)
			return message;
		
		// Guardar comentario
		User user = userComponent.getLoggedUser();
        long number = commentRepository.countByArticle(article) + 1;
        
        Comment comment = new Comment(article, user, number, r.getComment());
        commentRepository.save(comment);
        
        return message;
	}
	
}
