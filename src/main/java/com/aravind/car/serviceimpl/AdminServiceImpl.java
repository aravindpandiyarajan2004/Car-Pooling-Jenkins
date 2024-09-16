package com.aravind.car.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aravind.car.service.AdminService;
import com.aravind.car.model.Admin;
import com.aravind.car.repository.AdminRepo;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepo adminRepo;

	@Override
	public Admin login(String email, String password) {
		Admin admin = adminRepo.findByEmailAndPassword(email, password);
		return admin != null ? admin : null;
	}

}
