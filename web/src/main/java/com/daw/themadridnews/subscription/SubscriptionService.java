package com.daw.themadridnews.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {
    
	@Autowired
    protected SubscriptionRepository subscriptionRepository;
	

	public void subscribe(String email) {
		Subscription subscription = new Subscription(email);
		subscriptionRepository.save( subscription );
	}

}
