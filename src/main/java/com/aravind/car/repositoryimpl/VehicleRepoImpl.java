package com.aravind.car.repositoryimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aravind.car.model.Vehicle;
import com.aravind.car.repository.VehicleRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class VehicleRepoImpl implements VehicleRepo {

	@Autowired
	EntityManager entityManager;

	@Override
	public String save(Vehicle vehicle) {
		if (vehicle != null) {
			entityManager.merge(vehicle);
			return "Success";
		} else {
			return "Failure";
		}
	}

	@Override
	public String update(Vehicle vehicle) {
		if (vehicle != null) {
			entityManager.merge(vehicle);
			return "Success";
		} else {
			return "Failure";
		}

	}

	@Override
	public String delete(int vehicleId) {
		Vehicle id = entityManager.find(Vehicle.class, vehicleId);
		if (id != null) {
			entityManager.remove(id);
			return "Success";
		}
		return "Failure";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vehicle> findAllVehicles() {
		String hql = "from Vehicle";
		Query query = entityManager.createQuery(hql);
		return query.getResultList();
	}

	@Override
	public Vehicle findById(int vehicleId) {
		return entityManager.find(Vehicle.class, vehicleId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vehicle> findByUserId(int userId) {
		String hql = "from Vehicle v where v.user.userId = :userId";
		Query query = entityManager.createQuery(hql);
		query.setParameter("userId", userId);
		return query.getResultList();
	}

}
