package com.daw.themadridtimes.article;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkdownConverter {

	protected static String newline = "\r\n";
	protected static String newlineRegex = "\\r?\\n";

	public static String getFormatedHtml(String content) {
		StringBuilder cb = new StringBuilder( content );
		Pattern p;
		ArrayList<String> replace = new ArrayList<String>();
		
		// Letra negrita
		p = Pattern.compile("\\*\\*([^*]*)(\\*(?!\\*)[^*]*)*\\*\\*");
		replace.add("<strong>");
		replace.add("</strong>");
		cb = replaceRegex(cb, p, replace);
		
		// Letra cursiva
		p = Pattern.compile("\\*([^*]*)\\*");
		replace.clear();
		replace.add("<i>");
		replace.add("</i>");
		cb = replaceRegex(cb, p, replace);

		// Imagenes laterales
		p = Pattern.compile("\\[\\[([^|]+)\\|right\\|([^\\]]+)\\]\\]");
		replace.clear();
		replace.add("<img class=\"blog-grid-img-v1\" src=\"");
		replace.add("\" alt=\"");
		replace.add("\">");
		cb = replaceRegex(cb, p, replace);

		// Imagen ancho completo
		p = Pattern.compile("\\[\\[([^|]+)\\|full\\|([^\\]]+)\\]\\]");
		replace.clear();
		replace.add("<img class=\"img-responsive margin-bottom-30\" src=\"");
		replace.add("\" alt=\"");
		replace.add("\">");
		cb = replaceRegex(cb, p, replace);

		// Texto resaltado lateral
		p = Pattern.compile("~~"+newlineRegex+"([^~]+)(?:"+newlineRegex+")([^~]+)"+newlineRegex+"~~");
		replace.clear();
		replace.add("~~"+newline);
		replace.add("<br>");
		replace.add(newline+"~~");
		cb = replaceRegex(cb, p, replace);

		p = Pattern.compile("~~"+newlineRegex+"([^~]+)"+newlineRegex+"~~");
		replace.clear();
		replace.add("<span class=\"single-page-quote single-page-quote-left\"><p>");
		replace.add("</p></span>");
		cb = replaceRegex(cb, p, replace);

		// Parafos
		p = Pattern.compile("("+newlineRegex+"|^)(?!<img)(.+)("+newlineRegex+"|$)");
		replace.clear();
		replace.add("");
		replace.add("<p>");
		replace.add("</p><br>");
		replace.add("");
		cb = replaceRegex(cb, p, replace);
		
		return cb.toString();
	}
	
	private static StringBuilder replaceRegex(StringBuilder cb, Pattern p, ArrayList<String> replace) {
		Matcher m = p.matcher(cb);
		
		int start = 0;
		while (m.find(start)) {
			String replacement = replaceRegexAux(m, replace);
			
			cb.replace(m.start(), m.end(), replacement);
			start = m.start() + replacement.length();
		}
		
		return cb;
	}
	
	private static String replaceRegexAux(Matcher m, ArrayList<String> replace) {
		StringBuilder rb = new StringBuilder();
		int i = 1;
		
		Iterator<String> it = replace.iterator();
		while(it.hasNext()) {
			rb.append( it.next() );
			
			if(it.hasNext()) {
				rb.append( m.group(i) );
				i++;
			}
		}
		
		return rb.toString();
	}
	
}
