package com.daw.themadridnews;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.daw.themadridnews.user.User;
import com.daw.themadridnews.user.UserComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	@Autowired
	private UserComponent userComponent;

	@Autowired
	private Config config;

	private final String USER_FILE_FOLDER = "files";
	//private String USER_FILE_FOLDER = config.getPathImgUsers();
	//private final String ARTICLE_FILE_FOLDER = config.getPathImgArticles();
	//private final String AD_FILE_FOLDER = config.getPathImgAds();

	private List<String> imageTitles = new ArrayList<>();

	@RequestMapping(value = "/ajustes/imagen", method = RequestMethod.POST)
	public String handleFileUpload(Model model, @RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			User user = userComponent.getLoggedUser();
			String fileName = user.getId()+".jpg";
			try {
				File filesFolder = new File(USER_FILE_FOLDER);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}
				File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
				file.transferTo(uploadedFile);
				imageTitles.add(fileName);

				return "redirect:/ajustes";

			} catch (Exception e) {
				
				model.addAttribute("fileName",fileName);
				model.addAttribute("error",
						e.getClass().getName() + ":" + e.getMessage());

				return "redirect:/ajustes";
			}
		} else {
			
			model.addAttribute("error",	"The file is empty");

			return "redirect:/ajustes";
		}
	}

	public static boolean saveImage(MultipartFile file, String path, String ImageName){
		if (!file.isEmpty()) {
			try {
				File filesFolder = new File(path);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}
				File uploadedFile = new File(filesFolder.getAbsolutePath(), ImageName);
				file.transferTo(uploadedFile);
				return true;
			} catch (Exception e) {
				return false;
			}
		}else {
			return false;
		}
	}

	@RequestMapping("/imagen/usuario/{id}")
	public void handleFileDownload(@PathVariable String id,
			HttpServletResponse res) throws FileNotFoundException, IOException {

		File file = new File(USER_FILE_FOLDER, id+".jpg");

		if (file.exists()) {
			res.setContentType("image/jpeg");
			res.setContentLength(new Long(file.length()).intValue());
			FileCopyUtils
					.copy(new FileInputStream(file), res.getOutputStream());
		} else {
			res.sendError(404, "File" + id + "(" + file.getAbsolutePath()
					+ ") does not exist");
		}
	}

}