package com.nagarro.services;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.models.Image;
import com.nagarro.repositories.ImageRepository;

@Service
public class ImageService {
	
	@Autowired
	ImageRepository imageRepository;
	
	public void saveImage(List<Image> newImages) {
		Iterator i=newImages.iterator();
		while(i.hasNext()) {
			Image ii=(Image)i.next();
			System.out.println(ii.getImagePath());
			System.out.println(ii.getIsPrimaryImage());
		}
		Iterator it=newImages.iterator();
		while(it.hasNext()) {
			imageRepository.save((Image)it.next());
		}
		//Image image = imageRepository.save(newImage);
		//	return image;
	}

}
