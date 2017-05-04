package com.daw.themadridtimes.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		showImage( config.getPathImgArticles()+filename+".jpg", res);
	}

	@RequestMapping(value="/imagen/anuncio/{filename}")
	public void handleFileAdsDownload(@PathVariable String filename, HttpServletRequest req, HttpServletResponse res) {
		showImage( config.getPathImgAds()+filename+".jpg", res);
	}

	@RequestMapping(value="/imagen/usuario/{filename}")
	public void handleFileUsersDownload(@PathVariable String filename, HttpServletRequest req, HttpServletResponse res) {
		showImage( config.getPathImgUsers()+filename+".jpg", res);
	}
	
	protected void showImage(String filePath, HttpServletResponse res) {
		try {
			if (filePath != null) {
		        File file = new File( filePath );
		        
		        if(!file.exists())
		        	throw new Exception();
		        
				res.setContentLength(new Long(file.length()).intValue());
				res.setContentType(MediaType.IMAGE_JPEG_VALUE);
				FileCopyUtils.copy( new FileInputStream(file), res.getOutputStream() );
				
			} else {
				InputStream inpFile = FileUploadController.class.getResourceAsStream("/static/img/no-image.jpg");
	            
				res.setContentType("image/jpeg");
				FileCopyUtils.copy(inpFile, res.getOutputStream());
			}
	
			res.flushBuffer();

		} catch(Exception e) {
			if(filePath != null) this.showImage(null, res);
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