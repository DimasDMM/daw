package com.daw.themadridnews.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadCommons {

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
	
	public static void copyFileUsingStream(File source, File dest) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    }
	}
}
