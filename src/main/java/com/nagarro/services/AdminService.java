package com.nagarro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.models.Admin;
import com.nagarro.repositories.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	// method to authenticate user by email and password
	public Admin authenticateAdmin(String email, String password) throws Exception {
		Admin admin = adminRepository.findById(email).orElse(null);
		if (admin != null) {
			if (admin.getPassword().equals(password)) {
				return admin;
			}
		}
		return null;
	}

	// method to find all admins
	public List<Admin> findAllAdmins() throws Exception {
		List<Admin> admins = adminRepository.findAll();
		return admins;
	}

}
