package com.daw.themadridnews.ad;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.daw.themadridnews.user.User;

public interface AdRepository extends JpaRepository<Ad, Long> {

	@Query(nativeQuery=true, value="SELECT * FROM daw.ads WHERE is_lim_clicks AND lim_clicks < n_clicks OR !is_lim_clicks OR is_lim_views AND lim_views < n_views OR !is_lim_views OR is_lim_date_start AND lim_date_start >= NOW() OR !is_lim_date_start OR is_lim_date_end AND lim_date_end < NOW() OR !is_lim_date_end ORDER BY RAND() LIMIT 1;")
	public Ad findRandom();

	public Page<Ad> findByAuthor(User author, Pageable page);
}
