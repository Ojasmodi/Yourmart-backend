package com.nagarro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.models.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

	public Seller findByOwnerName(String ownerName);

}
