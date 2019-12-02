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

	public Seller authenticateSeller(String email, String password) {
		seller = sellerRepository.findByEmail(email);
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

	public Seller addSeller(Seller currentSeller) throws Exception {
		seller=sellerRepository.save(currentSeller);
		return seller;
	}

	public List<Seller> getAllSellers() {
		List<Seller> allSellers=sellerRepository.findAll();
		return allSellers;
	}

	@Transactional
	public boolean updateStatusOfSeller(Seller currentSeller) throws Exception {
		sellerRepository.updateStatusOfSeller(currentSeller.getSellerId(),currentSeller.getStatus());
		return true;
	}

}
