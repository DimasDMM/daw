package com.daw.themadridnews.ad;

import java.util.Date;

public class AdService {
	
	public static Ad getRandom(AdRepository rep) {
		Ad ad = rep.findRandom();
		
		ad.addView();
		rep.save(ad);
		
		return ad;
	}
	
	public static int getStatus(Ad ad) {
		if(ad.isLimClicks() && ad.getLimClicks() <= ad.getClicks())
			return 2;

		if(ad.isLimViews() && ad.getViews() <= ad.getViews())
			return 2;
		
		if(ad.isLimDateStart() && ad.getLimDateStart().after( new Date() ))
			return 0;
		
		if(ad.isLimDateEnd() && ad.getLimDateEnd().after( new Date() ))
			return 2;

		return 1;
	}
}
