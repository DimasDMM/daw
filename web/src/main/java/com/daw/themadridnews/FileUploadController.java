package com.daw.themadridnews;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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

import static com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation.ANONYMOUS.required;

@Controller
public class FileUploadController {

	@Autowired
	UserComponent userComponent;

	private static final String FILES_FOLDER = "files";

	private List<String> imageTitles = new ArrayList<>();

	@RequestMapping(value = "/imagen/subir", method = RequestMethod.POST)
	public String handleFileUpload(Model model, 
			@RequestParam(value = "imageTitle", required = false) String imageTitle,
			@RequestParam("file") MultipartFile file) {

		String fileName = imageTitles.size() + ".jpg";

		if (!file.isEmpty()) {
			try {
				File filesFolder = new File(FILES_FOLDER);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}

				File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
				file.transferTo(uploadedFile);

				imageTitles.add(imageTitle);
				
				model.addAttribute("imageTitles", imageTitles);
				
				return "index";

			} catch (Exception e) {
				
				model.addAttribute("fileName",fileName);
				model.addAttribute("error",
						e.getClass().getName() + ":" + e.getMessage());
				
				return "index";
			}
		} else {
			
			model.addAttribute("error",	"The file is empty");
			
			return "index";
		}
	}

	@RequestMapping("/image/{fileName}")
	public void handleFileDownload(@PathVariable String fileName,
			HttpServletResponse res) throws FileNotFoundException, IOException {

		File file = new File(FILES_FOLDER, fileName+".jpg");

		if (file.exists()) {
			res.setContentType("image/jpeg");
			res.setContentLength(new Long(file.length()).intValue());
			FileCopyUtils
					.copy(new FileInputStream(file), res.getOutputStream());
		} else {
			res.sendError(404, "File" + fileName + "(" + file.getAbsolutePath()
					+ ") does not exist");
		}
	}

}