package com.nagarro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nagarro.models.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

	@Modifying
	@Query("update Image set imagePath=?1 where imageId=?2")
	public void updatePathOfPrimaryImage(String fileLocation, long imageId);



}
