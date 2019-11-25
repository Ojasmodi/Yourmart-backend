package com.nagarro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.models.Seller;
import com.nagarro.repositories.SellerRepository;

@Service
public class SellerService {
	
	@Autowired
	SellerRepository sellerRepository;
	
	@Autowired
	Seller seller;

	public Seller authenticateSeller(String ownerName, String password) {
		seller = sellerRepository.findByOwnerName(ownerName);
		if (seller != null) {
			if (seller.getPassword().equals(password)) {
				return seller;
			}
		}
		return null;
	}
	
	public Seller findBySellerId(long id) throws Exception {
		seller = sellerRepository.findById(id).orElse(null);
		return seller;
	}

	public Seller add(Seller currentSeller) throws Exception {
		currentSeller.setStatus("NEED_APPROVAL");
		seller=sellerRepository.save(currentSeller);
		return seller;
	}

}
