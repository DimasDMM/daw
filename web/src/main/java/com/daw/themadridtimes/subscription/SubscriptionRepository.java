package com.daw.themadridtimes.subscription;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long>{
	public String findByEmail(String email);
	public Subscription deleteByEmail(String email);
}
