package com.nagarro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nagarro.models.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

	public Seller findByOwnerName(String ownerName);

	@Modifying
	@Query("update Seller set status=?2 where sellerId=?1")
	public void updateStatusOfSeller(long sellerId, String status);

}
