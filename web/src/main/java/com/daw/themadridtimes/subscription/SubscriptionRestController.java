package com.daw.themadridtimes.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.daw.themadridtimes.requests.ApiSubscription;
import com.daw.themadridtimes.utils.Message;
import com.daw.themadridtimes.webconfig.Config;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api")
public class SubscriptionRestController {
	
	public static interface Basic extends Subscription.Basic, Config.Responses {}
	
	@Autowired
	protected SubscriptionService subscriptionService;
	
	
	@JsonView(Basic.class)
	@RequestMapping(value="/subscripcion", method=RequestMethod.POST)
	public ResponseEntity<Object> subscribe(@RequestBody ApiSubscription r) {
		Message message = r.validation();
		if(message.getCode() != 0)
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		
		String e = subscriptionService.get(r.getEmail());
		if(e != null && !e.isEmpty()) {
			message.setCode(100);
			message.setMessage("Parece que el email que has introducido ya se encuentra en nuestra base de datos");
			return new ResponseEntity<>(message, HttpStatus.CONFLICT);
		}
		
		Subscription s = subscriptionService.subscribe(r.getEmail());
		
		return new ResponseEntity<>(s, HttpStatus.OK);
	}

	@RequestMapping(value="/subscripcion", method=RequestMethod.DELETE)
	public ResponseEntity<Object> unsubscribe(@RequestBody ApiSubscription r) {
		Message message = r.validation();
		if(message.getCode() != 0)
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		
		String e = subscriptionService.get(r.getEmail());
		if(e == null || e.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		subscriptionService.unsubscribe(r.getEmail());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
