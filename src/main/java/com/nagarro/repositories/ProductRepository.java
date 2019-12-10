package com.nagarro.repositories;

import java.util.Date;
import java.util.List;

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

	@Modifying
	@Query("update Product set pdfPath=?1 where prodId=?2")
	public void updatePdfPath(String fileLocation, String prodId);

	
	@Query("from Product where  prodStatus=?2 or prodStatus=?3")
	public List<Product> findProductsWithStatusNEWorREVIEWSinceMoreThanFiveDays(Date date,String status1, String status2);
	
	

}
