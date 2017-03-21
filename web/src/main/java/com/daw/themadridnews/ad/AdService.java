package com.daw.themadridnews.ad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdService {
	
	public static interface AdBasic extends Ad.Basic {}

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

	public Ad getAd(long id) {
		return adRepository.findOne(id);
	}

	public void addViewToAd(Ad ad) {
		if(ad == null) return;
		
		ad.addView();
		adRepository.save(ad);
	}

}
