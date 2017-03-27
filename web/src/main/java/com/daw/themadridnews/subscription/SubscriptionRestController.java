package com.daw.themadridnews.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.daw.themadridnews.requests.ApiSubscription;
import com.daw.themadridnews.utils.Message;

@RestController
@RequestMapping("/api")
public class SubscriptionRestController {
	
	@Autowired
	protected SubscriptionService subscriptionService;
	
	
	@RequestMapping(value="/subscripcion", method=RequestMethod.POST)
	public ResponseEntity<Object> subscribe(@RequestBody ApiSubscription r) {
		Message message = r.validation();
		if(message.getCode() != 0)
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		
		Subscription s = subscriptionService.subscribe(r.getEmail());
		
		return new ResponseEntity<>(s, HttpStatus.OK);
	}
	
}
