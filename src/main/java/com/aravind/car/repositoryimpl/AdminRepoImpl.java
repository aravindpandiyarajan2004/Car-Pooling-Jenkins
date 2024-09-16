package com.aravind.car.repositoryimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aravind.car.model.Admin;
import com.aravind.car.repository.AdminRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class AdminRepoImpl implements AdminRepo {

	@Autowired
	EntityManager entityManager;

	@Override
	public Admin findByEmailAndPassword(String email, String password) {
		String jpql = "SELECT a FROM Admin a WHERE a.email = :email AND a.password = :password";
		TypedQuery<Admin> query = entityManager.createQuery(jpql, Admin.class);
		query.setParameter("email", email);
		query.setParameter("password", password);
		return query.getResultStream().findFirst().orElse(null);
	}
}
