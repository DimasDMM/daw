package com.daw.themadridnews.requests;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	
	/*
	 * Comprueba si la longitud de un string es valida. Poner -1 si nos da igual un parametro
	 */
	public static boolean strValidLength(String str, int min, int max) {
		return ( str.length() > min && min >= 0 || min < 0 ) || ( str.length() < max && max >= 1 || max < 0 );
	}
	
	/*
	 * Comprueba si un string es una URL valida
	 */
	public static boolean strValidUrl(String str) {
		String regex = "^https?://.+$";
        return isMatch(str,regex);
	}
	
	private static boolean isMatch(String s, String pattern) {
        try {
            Pattern patt = Pattern.compile(pattern);
            Matcher matcher = patt.matcher(s);
            return matcher.matches();
        } catch (RuntimeException e) {
	        return false;
	    }       
	}
	
	/*
	 * Comprueba si hay strings vacios en una lista
	 */
	public static boolean strValidList(List<String> list) {
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			if(it.next().isEmpty())
				return false;
		}
		
		return true;
	}
	
	/*
	 * Comprueba si un numero es valido
	 */
	public static boolean intValidMin(int n, int min) {
		return ( n >= min );
	}
	
	public static boolean intValidMax(int n, int max) {
		return ( n <= max );
	}
	public static boolean intValidMinMax(int n, int min, int max) {
		return intValidMin(n,min) && intValidMax(n,max);
	}
}
