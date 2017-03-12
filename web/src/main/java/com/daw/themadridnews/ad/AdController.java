package com.daw.themadridnews.ad;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdController {

	@Autowired
	protected AdRepository adRepository;
	

	@RequestMapping(value="/anuncio/{id}", method=RequestMethod.GET)
	public String redirect(HttpServletResponse r, @PathVariable long id) {
		Ad ad = adRepository.findOne(id);
		
		if(ad == null) {
			return "redirect:/error/404";
		}
		
		ad.addClick();
		adRepository.save(ad);
		
		String url = ad.getUrl();
		
		return "redirect:"+url;
	}

}
