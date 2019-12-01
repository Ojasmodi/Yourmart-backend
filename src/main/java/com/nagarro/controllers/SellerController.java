package com.nagarro.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.nagarro.models.Image;
import com.nagarro.models.Product;
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
		newSeller.setStatus("NEED_APPROVAL");
		seller = sellerService.addSeller(newSeller);
		return Response.status(Status.OK).entity(seller).build();
	}
	
	@POST
	@Path("/update/status")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateStatusOfSeller(Seller currentSeller) throws Exception {
		sellerService.updateStatusOfSeller(currentSeller);
		return Response.status(Status.OK).entity("true").build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response getAllSellers() throws Exception {
		
		List<Seller> sellers= sellerService.getAllSellers();
		
		if (sellers == null) {
			return Response.status(Status.OK).entity(null).build();
		}
		return Response.status(Status.OK).entity(sellers).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get/{sellerId}")
	public Response getSellerById(@PathParam("sellerId") long id) throws Exception {
		
		Seller seller= sellerService.findBySellerId(id);
		
		if (seller == null) {
			return Response.status(Status.OK).entity(null).build();
		}
		return Response.status(Status.OK).entity(seller).build();
	}

}
