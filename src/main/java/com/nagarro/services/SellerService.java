package com.nagarro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.models.Product;
import com.nagarro.models.Seller;
import com.nagarro.repositories.SellerRepository;

@Service
public class SellerService {
	
	@Autowired
	SellerRepository sellerRepository;
	
	@Autowired
	Seller seller;

	//method to authenticate seller by email and password
	public Seller authenticateSeller(String email, String password) {
		seller = sellerRepository.findByEmail(email);
		if (seller != null) {
			if (seller.getPassword().equals(password)) {
				return seller;
			}
		}
		return null;
	}
	
	// method to find seller by sellerId
	public Seller findBySellerId(long id) throws Exception {
		seller = sellerRepository.findById(id).orElse(null);
		return seller;
	}

	// method to add a new seller
	public Seller addSeller(Seller currentSeller) throws Exception {
		seller=sellerRepository.save(currentSeller);
		return seller;
	}

	// method to get all sellers
	public List<Seller> getAllSellers() {
		List<Seller> allSellers=sellerRepository.findAll();
		return allSellers;
	}

	// method to update status of seller
	@Transactional
	public boolean updateStatusOfSeller(Seller currentSeller) throws Exception {
		sellerRepository.updateStatusOfSeller(currentSeller.getSellerId(),currentSeller.getStatus());
		return true;
	}

}
