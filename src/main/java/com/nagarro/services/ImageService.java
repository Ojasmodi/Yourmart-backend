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
	private ImageRepository imageRepository;
	
	@Autowired
	private ProductService productService;
	
	// method to add/save a new Image
	public void saveImage(Set<Image> newImages) {
		Iterator<Image> it=newImages.iterator();
		while(it.hasNext()) {
			imageRepository.save((Image)it.next());
		}
	}

    // method to get image by productId
	public Set<Image> getImageByProductId(String prodId) throws Exception {
		Product product=productService.findByProductId(prodId);
		Set<Image> images=product.getImages();
		return images;
	}

	// method to update path of primary image/primary image of a particular product
	@Transactional
	public void updatePathOfPrimaryImage(String fileLocation, long imageId) throws Exception {
		imageRepository.updatePathOfPrimaryImage(fileLocation, imageId);
		
	}



}
