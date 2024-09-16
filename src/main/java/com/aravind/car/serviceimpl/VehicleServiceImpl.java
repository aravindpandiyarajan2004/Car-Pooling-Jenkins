package com.aravind.car.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aravind.car.model.Vehicle;
import com.aravind.car.repository.VehicleRepo;
import com.aravind.car.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	VehicleRepo vehicleRepo;

	@Override
	public String addVehicle(Vehicle vehicle) {
		return vehicleRepo.save(vehicle);
	}

	@Override
	public String updateVehicle(Vehicle vehicle) {
		return vehicleRepo.update(vehicle);
	}

	@Override
	public String deleteVehicle(int vehicleId) {
		return vehicleRepo.delete(vehicleId);
	}

	@Override
	public List<Vehicle> getAllVehicle() {
		return vehicleRepo.findAllVehicles();
	}

	@Override
	public Vehicle getVehicle(int vehicleId) {
		return vehicleRepo.findById(vehicleId);
	}

	public List<Vehicle> getVehiclesByUserId(int userId) {
		return vehicleRepo.findByUserId(userId);
	}

}
