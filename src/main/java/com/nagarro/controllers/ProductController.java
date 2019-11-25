package com.nagarro.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.nagarro.models.Product;
import com.nagarro.services.ProductService;
import com.nagarro.services.SellerService;

@Path("/product")
public class ProductController {

	@Autowired
	SellerService sellerService;

	@Autowired
	ProductService productService;

	@Autowired
	Product product;

//	 /api/v1/seller/login
//	 method:POST
//	 Parameters: adminname:String and password:String as body params

//	@POST
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Path("/add")
//	public Response addProduct(Product newProduct) throws Exception {
//
//		//product = productService.addProduct(newProduct);
//		if (product == null) {
//			return Response.status(Status.OK).entity(null).build();
//		}
//		return Response.status(Status.OK).entity(product).build();
//	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/add")
	public Response addProduct(@Context HttpServletRequest request) throws Exception {

		product= productService.addProduct(request);
		if (product == null) {
			return Response.status(Status.OK).entity(null).build();
		}
		return Response.status(Status.OK).entity(product).build();
	}
}
