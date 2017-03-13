package com.daw.themadridnews.files;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.daw.themadridnews.Config;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class FileUploadController {

	private static final long serialVersionUID = -2604465744270702938L;
	
	@Autowired
	private Config config;


	@RequestMapping(value="/imagen/articulo/{filename}")
	public void handleFileArticleDownload(@PathVariable String filename, HttpServletRequest req, HttpServletResponse res) {
		File file = new File( config.getPathImgArticles(), filename+".jpg");
		showImage(file, res);
	}

	@RequestMapping(value="/imagen/anuncio/{filename}")
	public void handleFileAdsDownload(@PathVariable String filename, HttpServletRequest req, HttpServletResponse res) {
		File file = new File( config.getPathImgAds(), filename+".jpg");
		showImage(file, res);
	}

	@RequestMapping(value="/imagen/usuario/{filename}")
	public void handleFileUsersDownload(@PathVariable String filename, HttpServletRequest req, HttpServletResponse res) {
		File file = new File( config.getPathImgUsers(), filename+".jpg");
		showImage(file, res);
	}
	
	protected void showImage(File file, HttpServletResponse res) {
		try {
			if (file.exists()) {
				res.setContentLength(new Long(file.length()).intValue());
				res.setContentType(MediaType.IMAGE_JPEG_VALUE);
				FileCopyUtils.copy( new FileInputStream(file), res.getOutputStream() );
				
			} else {
				file = new ClassPathResource("static/img/no-image.jpg").getFile();
				res.setContentType("image/jpeg");
				res.setContentLength(new Long(file.length()).intValue());
				FileCopyUtils.copy(new FileInputStream(file), res.getOutputStream());
			}
	
			res.flushBuffer();

		} catch(Exception e) {
		}
	}

}