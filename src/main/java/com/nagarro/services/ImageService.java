package com.nagarro.services;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public void saveImage(Set<Image> newImages) {
		Iterator it=newImages.iterator();
		while(it.hasNext()) {
			imageRepository.save((Image)it.next());
		}
	}

//	public List<Image> getAllImages() {
//		List<Image> images=imageRepository.findAll();
//		return images;
//	}

	public Set<Image> getImageByProductId(String prodId) {
		//product.setProdId(prodId);
		Product product=productService.findByProductId(prodId);
		//Set<Image> images=imageRepository.findByProductId(prodId);
		Set<Image> images=product.getImages();
//		for(Image i:images)
//			System.out.println(i.getImagePath());
//		List<Image> images=imageRepository.findByProductId(prodId);
		return images;
	}

}
