package com.aravind.car.repository;

import java.util.List;

import com.aravind.car.model.Ride;

public interface RideRepo {
	public String save(Ride ride);

	public String update(Ride ride);

	public String delete(int rideId);

	public List<Ride> findAllRides();

	public Ride findById(int rideId);

	public List<Ride> findByUserId(int userId);

	public List<String> findDistinctStartPoints();

	public List<String> findDistinctEndPoints();

}
