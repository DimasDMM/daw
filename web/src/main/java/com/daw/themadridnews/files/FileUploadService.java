package com.daw.themadridnews.files;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadService {

	public static boolean saveImage(MultipartFile file, String path, String name){
		String filename = name +".jpg";

		if (!file.isEmpty()) {
			try {

				File filesFolder = new File(path);
				if (!filesFolder.exists())
					filesFolder.mkdirs();
				

				File uploadedFile = new File(filesFolder.getAbsolutePath(), filename);
				file.transferTo(uploadedFile);
				
				return true;

			} catch (Exception e) {
				return false;
			}
		}
		
		return false;
	}
}
