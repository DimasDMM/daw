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
	protected AdRepository adRepository;
	

	@RequestMapping(value="/anuncio/{id}", method=RequestMethod.GET)
	public ModelAndView redirect(@PathVariable long id) {
		Ad ad = adRepository.findOne(id);
		
		if(ad == null)
			return new ModelAndView( new RedirectView("/error/404") );
		
		ad.addClick();
		adRepository.save(ad);
		
		String url = ad.getUrl();
		
		return new ModelAndView( new RedirectView(url) );
	}

}
