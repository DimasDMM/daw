package com.daw.themadridnews.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.daw.themadridnews.favourite.Favourite;
import com.daw.themadridnews.webconfig.Config;

@Service
public class UserService {

	public static interface UserDetails extends User.Basic, User.Details, Favourite.Basic, Config.Responses {}

	@Autowired
	protected UserRepository userRepository;

	@Autowired
	protected UserComponent userComponent;
	
	protected static final int ITEMS_LIST = 10;
	
	
	// Devuelve un usuario concreto
	public User get(long id) {
		return userRepository.findOne(id);
	}

	// Guardar usuario
	public User save(User user) {
		return userRepository.save(user);
	}

	// Eliminar usuario
	public void delete(User user) {
		userRepository.delete(user);
	}

	// Devuelve lista de usuarios
	public Page<User> listWhenPermission(int nPage) {
		return userRepository.findAll( new PageRequest(nPage, ITEMS_LIST, Sort.Direction.DESC, "id") );
	}

	// Devuelve el usuario logeado
	public User getLoggedUser() {
		return userComponent.getLoggedUser();
	}

	// Establece el usuario logeado
	public void setLoggedUser(User user) {
		userComponent.setLoggedUser(user);
	}
	
}
