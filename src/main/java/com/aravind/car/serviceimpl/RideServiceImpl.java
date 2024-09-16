package com.aravind.car.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aravind.car.model.Ride;
import com.aravind.car.repository.RideRepo;
import com.aravind.car.service.RideService;

@Service
public class RideServiceImpl implements RideService {

	@Autowired
	RideRepo rideRepo;

	@Override
	public String addRide(Ride ride) {
		return rideRepo.save(ride);
	}

	@Override
	public String updateRide(Ride ride) {
		return rideRepo.update(ride);
	}

	@Override
	public String deleteRide(int rideId) {
		return rideRepo.delete(rideId);
	}

	@Override
	public List<Ride> getAllRide() {
		return rideRepo.findAllRides();
	}

	@Override
	public Ride getRide(int rideId) {
		return rideRepo.findById(rideId);
	}

	public List<Ride> getRidesByUserId(int userId) {
		return rideRepo.findByUserId(userId);
	}

	@Override
	public List<String> getDistinctStartPoints() {
		return rideRepo.findDistinctStartPoints();
	}

	@Override
	public List<String> getDistinctEndPoints() {
		return rideRepo.findDistinctEndPoints();
	}

}
