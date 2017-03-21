package com.daw.themadridnews.ad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api")
public class AdRestController {

	@Autowired
	protected AdService adService;
	

	@JsonView(AdService.AdBasic.class)
	@RequestMapping(value="/anuncio/{id}", method=RequestMethod.GET)
	public Ad redirect(@PathVariable long id) {
		Ad ad = adService.getAd(id);
		
		if(ad == null) return null;
		
		return ad;
	}

}
