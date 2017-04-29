package com.daw.themadridtimes.ad;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.daw.themadridtimes.user.User;

public interface AdRepository extends JpaRepository<Ad, Long> {

	@Query(nativeQuery=true, value="SELECT * FROM daw.ads WHERE lim_clicks IS NOT NULL AND lim_clicks < clicks OR lim_clicks IS NULL OR lim_views IS NOT NULL AND lim_views < views OR lim_views IS NULL OR lim_date_start IS NOT NULL AND lim_date_start >= NOW() OR lim_date_start IS NULL OR lim_date_end IS NOT NULL AND lim_date_end < NOW() OR lim_date_end IS NULL ORDER BY RAND() LIMIT 1;")
	public Ad findRandom();

	public Page<Ad> findByAuthor(User author, Pageable page);
}
