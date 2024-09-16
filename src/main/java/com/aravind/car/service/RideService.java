package com.aravind.car.service;

import java.util.List;

import com.aravind.car.model.Ride;

public interface RideService {

	public String addRide(Ride ride);

	public String updateRide(Ride ride);

	public String deleteRide(int rideId);

	public List<Ride> getAllRide();

	public Ride getRide(int rideId);

	public List<Ride> getRidesByUserId(int userId);

	List<String> getDistinctStartPoints();

	List<String> getDistinctEndPoints();

}
