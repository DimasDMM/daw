package com.daw.themadridnews.ad;

import java.util.Date;

public class AdCommons {
	
	public static Ad getRandom(AdRepository rep) {
		Ad ad = rep.findRandom();
		
		ad.addView();
		rep.save(ad);
		
		return ad;
	}
	
	public static int getStatus(Ad ad) {
		if(ad.getLimClicks() != null && ad.getLimClicks() <= ad.getClicks()) {
			return 2;
		}

		if(ad.getLimViews() != null && ad.getLimViews() <= ad.getViews()) {
			return 2;
		}
		
		if(ad.getLimDateStart() != null && ad.getLimDateStart().after( new Date() )) {
			return 0;
		}
		
		if(ad.getLimDateEnd() != null && ad.getLimDateEnd().before( new Date() )) {
			return 2;
		}

		return 1;
	}
}
