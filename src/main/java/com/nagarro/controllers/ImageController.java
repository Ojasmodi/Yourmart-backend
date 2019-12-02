package com.nagarro.controllers;

import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.nagarro.models.Image;
import com.nagarro.services.AdminService;
import com.nagarro.services.ImageService;

@Path("/files")
public class ImageController {
	
	@Autowired
	ImageService imageService;
	
	@Autowired
	AdminService adminService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/image/all/{product_id}")
	public Response getImagesByProductId(@PathParam("product_id") String prodId) {
        
		Set<Image> images = imageService.getImageByProductId(prodId);
		if (images.isEmpty()) {
			return Response.status(Status.OK).entity(null).build();
		}
		return Response.status(Status.OK).entity(new GenericEntity<Set<Image>>(images) {
		}).build();
	}
	

}
