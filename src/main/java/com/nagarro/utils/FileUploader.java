package com.nagarro.utils;

import java.io.File;

import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Component;

@Component
public class FileUploader {
	
	// method to upload image to a particular directory
		public String uploadFileToDirectory(FileItem item, String fileName, String directoryPath) {
			File file;
			String fileLocation = null;
			try {
				if (fileName.lastIndexOf("\\") >= 0) {
					fileLocation = directoryPath + Math.random() + fileName.substring(fileName.lastIndexOf("\\"));
				} else {
					fileLocation = directoryPath + Math.random() + fileName.substring(fileName.lastIndexOf("\\") + 1);
				}
				file = new File(fileLocation);
				item.write(file);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				return fileLocation;
			}
		}

}
