package com.nagarro.services;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.models.Image;
import com.nagarro.models.Product;
import com.nagarro.repositories.ImageRepository;

@Service
public class ImageService {
	
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	Product product;
	
	// method to add/save a new Image
	public void saveImage(Set<Image> newImages) {
		Iterator it=newImages.iterator();
		while(it.hasNext()) {
			imageRepository.save((Image)it.next());
		}
	}

    // method to get image by productId
	public Set<Image> getImageByProductId(String prodId) {
		Product product=productService.findByProductId(prodId);
		Set<Image> images=product.getImages();
		return images;
	}

	// method to update path of primary image/primary image of a particular product
	@Transactional
	public void updatePathOfPrimaryImage(String fileLocation, long imageId) {
		System.out.println(fileLocation+" DFD"+ imageId);
		imageRepository.updatePathOfPrimaryImage(fileLocation, imageId);
		
	}



}
