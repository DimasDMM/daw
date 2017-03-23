package com.daw.themadridnews.ad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.daw.themadridnews.requests.ApiAd;
import com.daw.themadridnews.utils.Message;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api")
public class AdRestController {
	
	public static interface Basic extends Ad.Basic {}

	@Autowired
	protected AdService adService;
	

	/**
	 * Datos basicos de un anuncio
	 */
	@JsonView(Basic.class)
	@RequestMapping(value="/anuncio/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> get(@PathVariable long id) {
		Ad ad = adService.getAd(id);
		
		if(ad == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(ad, HttpStatus.OK);
	}

	/**
	 * Añade visualizacion a un anuncio
	 */
	@RequestMapping(value="/anuncio/{id}/visualizacion", method=RequestMethod.GET)
	public ResponseEntity<Object> addView(@PathVariable long id) {
		Ad ad = adService.getAd(id);
		if(ad == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		ad = adService.addView(ad);
		
		return new ResponseEntity<>(ad, HttpStatus.OK);
	}

	/**
	 * Añade click a un anuncio
	 */
	@RequestMapping(value="/anuncio/{id}/click", method=RequestMethod.GET)
	public ResponseEntity<Object> addClick(@PathVariable long id) {
		Ad ad = adService.getAd(id);
		if(ad == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		ad = adService.addClick(ad);
		
		return new ResponseEntity<>(ad, HttpStatus.OK);
	}

	/**
	 * Añade un anuncio nuevo
	 */
	@RequestMapping(value="/anuncio/{id}", method=RequestMethod.POST)
	public ResponseEntity<Object> save(@PathVariable long id, @RequestBody ApiAd r) {
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
	@RequestMapping(value="/anuncio/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> modify(@PathVariable long id, @RequestBody ApiAd r) {
		Message message = r.validation();
		if(message.getCode() != 0)
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		
		// Comprobar existencia del anuncio
		Ad ad = adService.getAd(r.getId());
		if(ad == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		// Comprobar que el anuncio pertenezca al usuario
		
		
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

}
