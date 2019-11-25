package com.nagarro.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nagarro.services.AdminService;
import com.nagarro.services.ImageService;

@Path("/files")
public class ImageController {
	
	@Autowired
	ImageService imageService;
	
	@Autowired
	AdminService adminService;

//	@POST
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Path("/{product_id}/imageandpdf/add")
//	public ResponseEntity multiUpload(@RequestParam("files") MultipartFile[] files) {
//		List<Object> fileDownloadUrls = new ArrayList<>();
//		Arrays.asList(files)
//				.stream()
//				.forEach(file -> fileDownloadUrls.add(uploadToLocalFileSystem(file).getBody()));
//		return ResponseEntity.ok(fileDownloadUrls);
//	}

}
