package com.daw.themadridnews.ad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AdController {

	@Autowired
	protected AdService adService;
	

	@RequestMapping(value="/anuncio/{id}", method=RequestMethod.GET)
	public ModelAndView redirect(@PathVariable long id) {
		String url = adService.getAdUrl(id);
		
		if(url == null)
			return new ModelAndView( new RedirectView("/error/404") );
		
		return new ModelAndView( new RedirectView(url) );
	}

}
