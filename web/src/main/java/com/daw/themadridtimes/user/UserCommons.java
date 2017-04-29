package com.daw.themadridtimes.user;

public class UserCommons {
	
	protected static String[] roles = {"ROLE_USER", "ROLE_EDITOR", "ROLE_PUBLICIST", "ROLE_ADMIN"};
	
	
	public static String[] getRoles() {
		return roles;
	}
	
	public static String getRoleName(String role) {
		switch(role) {
			case "ROLE_USER":
				return "Usuario";
			case "ROLE_EDITOR":
				return "Editor";
			case "ROLE_PUBLICIST":
				return "Publicista";
			case "ROLE_ADMIN":
				return "Administrador";
		}
		
		return "Desconocido";
	}
}
