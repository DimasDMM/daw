package com.daw.themadridnews.ad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdService {

	@Autowired
	protected AdRepository adRepository;
	

	public Ad getAd(long id) {
		return adRepository.findOne(id);
	}

	// Añadir visualizacion a un anuncio
	public Ad addView(Ad ad) {
		if(ad == null) return null;
		
		ad.addView();
		return adRepository.save(ad);
	}
	
	// Añadir click a un anuncio
	public Ad addClick(Ad ad) {
		if(ad == null) return null;
		
		ad.addClick();
		return adRepository.save(ad);
	}

	public Ad save(Ad ad) {
		if(ad == null) return null;
		return adRepository.save(ad);
	}

}
