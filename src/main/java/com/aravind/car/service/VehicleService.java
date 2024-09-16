package com.aravind.car.service;

import java.util.List;

import com.aravind.car.model.Vehicle;

public interface VehicleService {

	public String addVehicle(Vehicle vehicle);

	public String updateVehicle(Vehicle vehicle);

	public String deleteVehicle(int vehicleId);

	public List<Vehicle> getAllVehicle();

	public Vehicle getVehicle(int vehicleId);
	
	public List<Vehicle> getVehiclesByUserId(int userId);

}
