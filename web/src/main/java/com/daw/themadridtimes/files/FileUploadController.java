package com.daw.themadridtimes.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.daw.themadridtimes.webconfig.Config;


@Controller
public class FileUploadController {
	
	@Autowired private Config config;


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
	
	@SuppressWarnings("null")
	protected void showImage(File file, HttpServletResponse res) {
		try {
			if (file != null && file.exists()) {
				res.setContentLength(new Long(file.length()).intValue());
				res.setContentType(MediaType.IMAGE_JPEG_VALUE);
				FileCopyUtils.copy( new FileInputStream(file), res.getOutputStream() );
				
			} else {
				InputStream inpFile = FileUploadController.class.getResourceAsStream("/static/img/no-image.jpg");
	            
				file = null;

				res.setContentType("image/jpeg");
				FileCopyUtils.copy(inpFile, res.getOutputStream());
			}
	
			res.flushBuffer();

		} catch(Exception e) {
			System.err.println("FileUploadController Error: "+ e.getMessage());
			if(file != null) this.showImage(null, res);
		}
	}
	/*
	protected void showImage(File file, HttpServletResponse res) {
		try {
			if (file != null && file.exists()) {
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
			System.err.println("FileUploadController Error: "+ e.getMessage());
			if(file != null) this.showImage(null, res);
		}
	}
	 */
}