package com.daw.themadridnews.editor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.daw.themadridnews.article.Article;
import com.daw.themadridnews.article.ArticleService;
import com.daw.themadridnews.files.FileUploadCommons;
import com.daw.themadridnews.requests.ApiArticle;
import com.daw.themadridnews.user.User;
import com.daw.themadridnews.user.UserService;
import com.daw.themadridnews.utils.Message;
import com.daw.themadridnews.webconfig.Config;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api")
public class EditorRestController {

	@Autowired
	protected ArticleService articleService;
	
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected Config config;


	/**
	 * Obtener datos de un articulo
	 */
	@JsonView(ArticleService.Editor.class)
	@RequestMapping(value="/editor/articulo/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> get(@PathVariable long id) {
		Article a = articleService.get(id);
		if(a == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		// Verificar que el usuario tenga permiso
		User user = userService.getLoggedUser();
		if(!articleService.hasPermission(user, a))
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		return new ResponseEntity<>(a, HttpStatus.OK);
	}
	
	/**
	 * Añade un nuevo articulo
	 */
	@JsonView(ArticleService.Editor.class)
	@RequestMapping(value="/editor/articulo", method=RequestMethod.POST)
	public ResponseEntity<Object> save(@RequestBody ApiArticle r) {
		Message message = r.validation();
		if(message.getCode() != 0)
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		
		Article a = new Article();
		
		// Crear objeto articulo
		User user = userService.getLoggedUser();
		
		a.setTitle( r.getTitle() );
		a.setContent( r.getContent() );
		a.setCategory( r.getCategory() );
		a.setAuthor( user );
		a.setSource( r.getSource() );
		a.setTags( r.getTags() );
		a.setVisible( r.getVisible() );
		
        // Guardar
		a = articleService.save(a);
		
		return new ResponseEntity<>(a, HttpStatus.OK);
	}
	
	/**
	 * Modifica un articulo existente
	 */
	@JsonView(ArticleService.Editor.class)
	@RequestMapping(value="/editor/articulo/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> modify(@PathVariable long id, @RequestBody ApiArticle r) {
		Message message = r.validation();
		if(message.getCode() != 0)
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		
		Article a = articleService.get(id);
		if(a == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		// Verificar que el usuario tenga permiso de edicion
		User user = userService.getLoggedUser();
		if(!articleService.hasPermission(user, a))
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		// Modificar objeto articulo
		a.setTitle( r.getTitle() );
		a.setContent( r.getContent() );
		a.setCategory( r.getCategory() );
		a.setSource( r.getSource() );
		a.setTags( r.getTags() );
		a.setVisible( r.getVisible() );
		
        // Guardar
		a = articleService.save(a);
		
		return new ResponseEntity<>(a, HttpStatus.OK);
	}

	@JsonView(ArticleService.Editor.class)
	@RequestMapping(value="/editor/articulo/{id}/imagen", method=RequestMethod.POST)
	public ResponseEntity<Object> modify(@PathVariable long id, @RequestParam("file") MultipartFile file) {
		Article a = articleService.get(id);
		if(a == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		// Verificar que el usuario tenga permiso de edicion
		User user = userService.getLoggedUser();
		if(!articleService.hasPermission(user, a))
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		// Modificar imagen
		boolean result = FileUploadCommons.saveImage( file, config.getPathImgArticles(), String.valueOf( a.getId()) );
		if(!result)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(a, HttpStatus.OK);
	}
	
	/**
	 * Elimina un articulo
	 */
	@RequestMapping(value="/editor/articulo/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteArticle(@PathVariable long id) {
		// Comprobar existencia de articulo
		Article a = articleService.get(id);
		if(a == null) return new ResponseEntity<>(a, HttpStatus.NOT_FOUND);
		
		// Comprobar permisos
		User user = userService.getLoggedUser();
		if(!articleService.hasPermission(user, a))
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		articleService.delete(a);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Lista de articulos
	 * El numero de pagina empieza en 1
	 */
	@JsonView(ArticleService.Editor.class)
	@RequestMapping(value="/editor/articulo/lista", method=RequestMethod.GET)
	public ResponseEntity<Object> list(@RequestParam(required=false) Integer page) {
		if(page != null && page < 1)
			page = 1;
		
		Page<Article> p = articleService.listWhenPermission(page-1);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}

}
