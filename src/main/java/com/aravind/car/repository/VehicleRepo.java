package com.aravind.car.repository;

import java.util.List;

import com.aravind.car.model.Vehicle;

public interface VehicleRepo {
	public String save(Vehicle vehicle);

	public String update(Vehicle vehicle);

	public String delete(int vehicleId);

	public List<Vehicle> findAllVehicles();

	public Vehicle findById(int vehicleId);

	public List<Vehicle> findByUserId(int userId);

}
