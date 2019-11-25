package com.nagarro.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.nagarro.models.Seller;
import com.nagarro.services.SellerService;

@Path("/seller")
public class SellerController {

	@Autowired
	SellerService sellerService;

	@Autowired
	Seller seller;

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addSeller(Seller newSeller) throws Exception {
		seller = sellerService.add(newSeller);
		return Response.status(Status.OK).entity(seller).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get/all")
	public Response getAllSellers() throws Exception {

		return Response.status(Status.OK).entity(seller).build();
	}

}
