package com.nagarro.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.nagarro.models.Admin;
import com.nagarro.models.Seller;
import com.nagarro.services.AdminService;
import com.nagarro.services.SellerService;

@Path("/users")
public class UserController {

	@Autowired
	private SellerService sellerService;

	@Autowired
	private AdminService adminService;

	// /api/v1/seller/login
	// method:POST
	// Parameters: adminname:String and password:String as body params
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/seller/login")
	public Response loginSeller(Seller currentSeller) throws Exception {

		currentSeller = sellerService.authenticateSeller(currentSeller.getEmail(), currentSeller.getPassword());
		if (currentSeller == null) {
			return Response.status(Status.OK).entity(null).build();
		}
		return Response.status(Status.OK).entity(currentSeller).build();
	}

	// /api/v1/admin/login
	// method:POST
	// Parameters: username:String and password:String as body params
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/admin/login")
	public Response loginAdmin(Admin currentAdmin) throws Exception {
		currentAdmin = adminService.authenticateAdmin(currentAdmin.getEmail(), currentAdmin.getPassword());
		if (currentAdmin == null) {
			return Response.status(Status.OK).entity(null).build();
		}
		return Response.status(Status.OK).entity(currentAdmin).build();
	}
}
