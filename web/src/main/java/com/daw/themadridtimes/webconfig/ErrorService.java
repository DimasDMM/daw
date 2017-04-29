package com.daw.themadridtimes.webconfig;

import org.springframework.stereotype.Service;

@Service
public class ErrorService {

	public String generateErrorMessage(int code) {
		String message = "";
		
		switch (code) {
			case 400:
				message = "Bad Request";
				break;
				
			case 401:
				message = "Sin autorización";
				break;
				
			case 403:
				message = "Sin autorización";
				break;
				
			case 404:
				message = "Página no encontrada";
				break;
				
			case 500:
				message = "Error interno";
				break;
				
			default:
				message = "Error desconocido ("+ code +")";
		}
		
		return message;
	}
}