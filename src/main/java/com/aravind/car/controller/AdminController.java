package com.aravind.car.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aravind.car.model.Admin;
import com.aravind.car.model.Booking;
import com.aravind.car.model.Payment;
import com.aravind.car.model.Ride;
import com.aravind.car.model.User;
import com.aravind.car.model.Vehicle;
import com.aravind.car.service.AdminService;
import com.aravind.car.service.BookingService;
import com.aravind.car.service.PaymentService;
import com.aravind.car.service.RideService;
import com.aravind.car.service.UserService;
import com.aravind.car.service.VehicleService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("http://localhost:3000")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private UserService userService;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private RideService rideService;

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/login")
	public Admin login(@RequestBody Admin admin) {
		return adminService.login(admin.getEmail(), admin.getPassword());
	}

	@GetMapping("/user")
	public List<User> getAllUsers() {
		return userService.getAllUser();
	}

	@GetMapping("/booking")
	public List<Booking> getAllBookings() {
		return bookingService.getAllBooking();
	}

	@GetMapping("/vehicle")
	public List<Vehicle> getAllVehicles() {
		return vehicleService.getAllVehicle();
	}

	@GetMapping("/ride")
	public List<Ride> getAllRides() {
		return rideService.getAllRide();
	}

	@GetMapping("/payment")
	public List<Payment> getAllPayment() {
		return paymentService.getAllPayment();

	}

	@PutMapping("/user/{id}")
	public String updateAccountStatus(@PathVariable int id, @RequestBody User updatedUser) {
		User existingUser = userService.getUser(id);
		if (existingUser != null) {
			existingUser.setAccountStatus(updatedUser.getAccountStatus());
			return userService.addUser(existingUser);
		}
		return null;
	}

	@GetMapping("/pendingBooking")
	public List<Booking> getAllPendingBookings() {
		return bookingService.getPendingBookings();
	}

	
	@PutMapping("/bookingStatus/{id}")
	public ResponseEntity<String> updateBookingStatus(@PathVariable int id, @RequestBody Booking updateRequest) {
		boolean updated = bookingService.updateBookingStatus(id, updateRequest.getBookingStatus());
		if (updated) {
			return ResponseEntity.ok("Booking status updated successfully");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
	}

	@GetMapping("/startPoints")
	public List<String> getStartPoints() {
		return rideService.getDistinctStartPoints();
	}

	@GetMapping("/endPoints")
	public List<String> getEndPoints() {
		return rideService.getDistinctEndPoints();
	}
	
	@GetMapping("/userStatusSummary")
	public ResponseEntity<Map<String, Integer>> getUserStatusSummary() {
	    Map<String, Integer> statusCounts = new HashMap<>();
	    statusCounts.put("approved", userService.findUserStatus("approved").size());
	    statusCounts.put("rejected", userService.findUserStatus("rejected").size());
	    statusCounts.put("pending", userService.findUserStatus("pending").size());
	    return ResponseEntity.ok(statusCounts);
	}


}
