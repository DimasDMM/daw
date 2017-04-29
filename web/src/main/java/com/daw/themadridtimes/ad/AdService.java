package com.daw.themadridtimes.ad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.daw.themadridtimes.user.User;
import com.daw.themadridtimes.user.UserComponent;
import com.daw.themadridtimes.webconfig.Config;

@Service
public class AdService {

	public static interface Basic extends Ad.Basic, Config.Responses {}
	public static interface Publicist extends Ad.Basic, Ad.Details, User.Basic, Config.Responses {}

	@Autowired
	protected AdRepository adRepository;
	
	@Autowired
	protected UserComponent userComponent;
	
	protected static final int ITEMS_LIST = 20;
	

	public Ad get(long id) {
		return adRepository.findOne(id);
	}
	
	// Anuncio al azar
	public Ad getRandom() {
		return adRepository.findRandom();
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

	// Guardar anuncio
	public Ad save(Ad ad) {
		if(ad == null) return null;
		return adRepository.save(ad);
	}

	// Eliminar anuncio
	public void delete(Ad ad) {
		if(ad == null) return;
		adRepository.delete(ad);
	}

	// Comprobar si tiene permisos de edicion
	public boolean hasPermission(User user, Ad ad) {
		return (user.equals( ad.getAuthor() ) || user.hasRole("ROLE_ADMIN"));
	}

	// Devuelve lista de anuncios
	public Page<Ad> listWhenPermission(int nPage) {
		User user = userComponent.getLoggedUser();
		Page<Ad> page;
		
		if(user.hasRole("ROLE_ADMIN")) {
			page = adRepository.findAll( new PageRequest(nPage, ITEMS_LIST, Sort.Direction.DESC, "id") );
		} else {
			User userLogged = userComponent.getLoggedUser();
			page = adRepository.findByAuthor( userLogged, new PageRequest(nPage, ITEMS_LIST, Sort.Direction.DESC, "id") );
		}
		
		return page;
	}

	// Devuelve anuncio al azar
	public Ad findRandom() {
		return adRepository.findRandom();
	}

}
