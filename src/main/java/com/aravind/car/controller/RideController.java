package com.aravind.car.controller;

import java.util.List;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aravind.car.model.Ride;
import com.aravind.car.model.User;
import com.aravind.car.model.Vehicle;
import com.aravind.car.repository.UserRepo;
import com.aravind.car.repository.VehicleRepo;
import com.aravind.car.serviceimpl.RideServiceImpl;

@RestController
@RequestMapping("/ride")
@CrossOrigin("http://localhost:3000")
public class RideController {

	@Autowired
	RideServiceImpl service;

	static final String SUCCESS = "Success";
	static final String FAILURE = "Failure";

	@Autowired
	private UserRepo userRepo; // Repository for fetching User entities

	@Autowired
	private VehicleRepo vehicleRepo; // Repository for fetching Vehicle entities

	@PostMapping
	public String insertRide(@RequestParam("startPoint") String startPoint, @RequestParam("endPoint") String endPoint,
			@RequestParam("departureTime") String departureTime, @RequestParam("arrivalTime") String arrivalTime,
			@RequestParam("rideDate") String rideDate, // Expecting in YYYY-MM-DD format
			@RequestParam("distance") int distance, @RequestParam("pricePerPerson") double pricePerPerson,
			@RequestParam("availableSeats") int availableSeats, @RequestParam("userId") int userId,
			@RequestParam("vehicleId") int vehicleId) {

		String msg = "";

		try {
			// Create a new Ride object using the request parameters
			Ride ride = new Ride();
			ride.setStartPoint(startPoint);
			ride.setEndPoint(endPoint);
			ride.setDepartureTime(departureTime);
			ride.setArrivalTime(arrivalTime);
			ride.setRideDate(Date.valueOf(rideDate)); // Convert string to Date
			ride.setDistance(distance);
			ride.setPricePerPerson(pricePerPerson);
			ride.setAvailableSeats(availableSeats);

			// Fetch User and Vehicle entities by their IDs
			User user = userRepo.findById(userId);
			Vehicle vehicle = vehicleRepo.findById(vehicleId);

			ride.setUser(user);
			ride.setVehicle(vehicle);

			// Call service to add the ride
			service.addRide(ride);

			msg = "SUCCESS"; // Assuming SUCCESS is a constant or string
		} catch (Exception e) {
			e.printStackTrace(); // Log the exception details for debugging
			msg = "FAILURE"; // Assuming FAILURE is a constant or string
		}

		return msg;
	}

	@GetMapping("{rideId}")
	public Ride getRideById(@PathVariable("rideId") int id) {

		return service.getRide(id);
	}

	@GetMapping("/all")
	public List<Ride> getRides() {

		return service.getAllRide();
	}

	@PutMapping
	public String updateRide(@RequestBody Ride ride) {
		String msg = "";

		try {
			service.updateRide(ride);
			msg = SUCCESS;
		} catch (Exception e) {
			msg = FAILURE;
		}

		return msg;
	}

	@DeleteMapping("{rideId}")
	public String deleteRideById(@PathVariable("rideId") int id) {
		String msg = "";

		try {
			service.deleteRide(id);
			msg = SUCCESS;
		} catch (Exception e) {
			msg = FAILURE;
		}

		return msg;

	}

	@GetMapping("/user/{userId}")
	public List<Ride> getRidesByUserId(@PathVariable("userId") int userId) {
		return service.getRidesByUserId(userId);
	}

}
