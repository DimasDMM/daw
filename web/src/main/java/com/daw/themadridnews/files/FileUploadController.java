package com.daw.themadridnews.files;

import java.io.File;
import java.io.FileInputStream;
import javax.servlet.http.HttpServletResponse;
import com.daw.themadridnews.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileUploadController {

	@Autowired
	private Config config;


	@RequestMapping("/imagen/articulo/{filename}")
	public void handleFileArticleDownload(@PathVariable String filename, HttpServletResponse res) {
		File file = new File( config.getPathImgArticles(), filename+".jpg");
		showImage(file, res);
	}

	@RequestMapping("/imagen/anuncio/{filename}")
	public void handleFileAdsDownload(@PathVariable String filename, HttpServletResponse res) {
		File file = new File( config.getPathImgAds(), filename+".jpg");
		showImage(file, res);
	}

	@RequestMapping("/imagen/usuario/{filename}")
	public void handleFileUsersDownload(@PathVariable String filename, HttpServletResponse res) {
		File file = new File( config.getPathImgUsers(), filename+".jpg");
		showImage(file, res);
	}
	
	protected void showImage(File file, HttpServletResponse res) {
		try {
			if (file.exists()) {
				res.setContentType("image/jpeg");
				res.setContentLength(new Long(file.length()).intValue());
				FileCopyUtils.copy(new FileInputStream(file), res.getOutputStream());
				
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