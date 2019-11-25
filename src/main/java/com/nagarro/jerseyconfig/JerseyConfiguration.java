package com.nagarro.jerseyconfig;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.nagarro.controllers.ImageController;
import com.nagarro.controllers.ProductController;
import com.nagarro.controllers.SellerController;
import com.nagarro.controllers.UserController;

@Component
@ApplicationPath("/api/v1")
public class JerseyConfiguration extends ResourceConfig {

	
	// adding the classes which will be needed for api request
	public JerseyConfiguration() {
		register(UserController.class);
		register(ProductController.class);
		register(ImageController.class);
		register(SellerController.class);
		register(CORSResponseFilter.class);
	}
}