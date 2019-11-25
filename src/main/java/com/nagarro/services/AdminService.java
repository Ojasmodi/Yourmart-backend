package com.nagarro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.models.Admin;
import com.nagarro.repositories.AdminRepository;

@Service
public class AdminService {

	
	@Autowired
	AdminRepository adminRepository;
	
	public Admin authenticateAdmin(String adminName, String password) {
		Admin admin = adminRepository.findByUsername(adminName);
		if (admin != null) {
			if (admin.getPassword().equals(password)) {
				return admin;
			}
		}
		return null;
	}

}
