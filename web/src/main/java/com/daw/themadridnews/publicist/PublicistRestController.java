package com.daw.themadridnews.publicist;

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
import com.daw.themadridnews.ad.Ad;
import com.daw.themadridnews.ad.AdService;
import com.daw.themadridnews.files.FileUploadCommons;
import com.daw.themadridnews.requests.ApiAd;
import com.daw.themadridnews.user.User;
import com.daw.themadridnews.user.UserService;
import com.daw.themadridnews.utils.Message;
import com.daw.themadridnews.webconfig.Config;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api")
public class PublicistRestController {
	
	@Autowired
	protected AdService adService;
	
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected Config config;
	
	
	/**
	 * Obtener datos de un anuncio
	 */
	@JsonView(AdService.Publicist.class)
	@RequestMapping(value="/publicista/anuncio/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> get(@PathVariable long id) {
		Ad ad = adService.get(id);
		if(ad == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		// Comprobar permisos
		User user = userService.getLoggedUser();
		if(!user.equals( ad.getAuthor() ) && !user.hasRole("ROLE_ADMIN"))
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

		return new ResponseEntity<>(ad, HttpStatus.OK);
	}
	
	/**
	 * Añade un nuevo anuncio
	 */
	@JsonView(AdService.Publicist.class)
	@RequestMapping(value="/publicista/anuncio", method=RequestMethod.POST)
	public ResponseEntity<Object> newAd(ApiAd r) {
		Message message = r.validation();
		if(message.getCode() != 0)
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		
		Ad ad = new Ad();
		
		ad.setTitle( r.getTitle() );
		ad.setUrl( r.getUrl() );
		ad.setWeight( r.getWeight() );
		ad.setLimDateStart( r.getLimDateStart() );
		ad.setLimDateEnd( r.getLimDateEnd() );
		ad.setLimClicks( r.getLimClicks() );
		ad.setLimViews( r.getLimViews() );
		adService.save(ad);
		
		return new ResponseEntity<>(ad, HttpStatus.OK);
	}
	
	/**
	 * Modifica anuncio existente
	 */
	@JsonView(AdService.Publicist.class)
	@RequestMapping(value="/publicista/anuncio/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> modify(@PathVariable long id, @RequestBody ApiAd r) {
		Message message = r.validation();
		if(message.getCode() != 0)
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		
		// Comprobar existencia del anuncio
		Ad ad = adService.get(r.getId());
		if(ad == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		// Comprobar que el anuncio pertenezca al usuario
		User user = userService.getLoggedUser();
		if(!user.equals( ad.getAuthor() ) && !user.hasRole("ROLE_ADMIN"))
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		// Guardar
		ad.setTitle( r.getTitle() );
		ad.setUrl( r.getUrl() );
		ad.setWeight( r.getWeight() );
		ad.setLimDateStart( r.getLimDateStart() );
		ad.setLimDateEnd( r.getLimDateEnd() );
		ad.setLimClicks( r.getLimClicks() );
		ad.setLimViews( r.getLimViews() );
		adService.save(ad);
		
		return new ResponseEntity<>(ad, HttpStatus.OK);
	}

	@JsonView(AdService.Publicist.class)
	@RequestMapping(value="/publicista/anuncio/{id}/imagen", method=RequestMethod.POST)
	public ResponseEntity<Object> modify(@PathVariable long id, @RequestParam(name="file") MultipartFile file) {
		Ad ad = adService.get(id);
		if(ad == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		boolean result = FileUploadCommons.saveImage( file, config.getPathImgAds(), String.valueOf(ad.getId()) );
		if(!result)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(ad, HttpStatus.OK);
	}
	
	/**
	 * Elimina un anuncio
	 */
	@RequestMapping(value="/publicista/anuncio/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable long id) {
		// Comprobar existencia del anuncio
		Ad ad = adService.get(id);
		if(ad == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		// Comprobar que el anuncio pertenezca al usuario
		User user = userService.getLoggedUser();
		if(!adService.hasPermission(user, ad))
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		adService.delete(ad);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Lista de anuncios
	 * El numero de pagina empieza en 1
	 */
	@JsonView(AdService.Publicist.class)
	@RequestMapping(value="/publicista/anuncio/lista", method=RequestMethod.GET)
	public ResponseEntity<Object> list(@RequestParam(required=false) Integer page) {
		if(page == null || page < 1)
			page = 1;
		
		Page<Ad> p = adService.listWhenPermission(page-1);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}
}
