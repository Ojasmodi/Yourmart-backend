package com.nagarro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nagarro.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

	@Modifying
	@Query("update Product set prodStatus=?2 where prodId=?1")
	public void updateStatusOfProduct(String prodId, String prodStatus);
	
	

}
