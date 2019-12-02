package com.nagarro.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.nagarro.models.Product;
import com.nagarro.models.Seller;
import com.nagarro.services.ImageService;
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

	@Autowired
	ImageService imageService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/add")
	public Response addProduct(@Context HttpServletRequest request) throws Exception {

		product = productService.addProduct(request);
		if (product == null) {
			return Response.status(Status.OK).entity(null).build();
		}
		return Response.status(Status.OK).entity(product).build();
	}
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/add/otherimages/{sellerId}/{prodId}")
	public Response addNewImagesOfProduct(@Context HttpServletRequest request,@PathParam("sellerId") long sellerId,@PathParam("prodId") String prodId) throws Exception {

		boolean result = productService.addNewImagesOfProduct(request,sellerId,prodId);
		return Response.status(Status.OK).entity(result).build();
	}
	
	@PUT
	@Path("/update/status")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateStatusOfProduct(Product currentProduct) throws Exception {

		productService.updateStatusOfProduct(currentProduct);
		return Response.status(Status.OK).entity("true").build();
	}
	
	@PUT
	@Path("/update/primaryImageOrUserGuide/{prodId}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateFilesOfProduct(@Context HttpServletRequest request,@PathParam("prodId") String prodId) throws Exception {

		boolean resultStatus=productService.updateFilesOfProduct(request,prodId);
		return Response.status(Status.OK).entity(resultStatus).build();
	}
		
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response getAllProducts(Product product) throws Exception {

		List<Product> products = productService.getAllProducts();

		if (products == null) {
			return Response.status(Status.OK).entity(null).build();
		}
		return Response.status(Status.OK).entity(products).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get/productId/{prodId}")
	public Response getProductByProductId(@PathParam("prodId") String id) throws Exception {

		product = productService.getProductByProductId(id);
		if (product == null) {
			return Response.status(Status.NOT_FOUND).entity(null).build();
		}
		return Response.status(Status.OK).entity(product).build();
	}
	
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/update/otherdetails/{sellerId}")  //FTE
	public Response updateOtherdetailsOfProduct(@PathParam("sellerId") long sId,Product product) throws Exception {

		product.setUpdatedOn(new Date());

		product = productService.updateOtherdetailsOfProduct(product,sId);
		
		if (product == null) {
			return Response.status(Status.OK).entity(null).build();
		}
		return Response.status(Status.OK).entity(product).build();
	}
}
