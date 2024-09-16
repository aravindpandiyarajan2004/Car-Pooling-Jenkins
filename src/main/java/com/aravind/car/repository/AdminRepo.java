package com.aravind.car.repository;

import com.aravind.car.model.Admin;

public interface AdminRepo {
	 Admin findByEmailAndPassword(String email, String password);

}
