package com.daw.themadridnews.ad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdService {

	@Autowired
	protected AdRepository adRepository;
	
	public String getAdUrl(long id) {
		Ad ad = adRepository.findOne(id);
		
		if(ad == null) return null;
		
		ad.addClick();
		adRepository.save(ad);
		
		String url = ad.getUrl();
		
		return url;
	}

}
