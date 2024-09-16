package com.aravind.car.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aravind.car.model.Booking;
import com.aravind.car.model.Payment;
import com.aravind.car.model.Response;
import com.aravind.car.model.Ride;
import com.aravind.car.model.User;
import com.aravind.car.repository.BookingRepo;
import com.aravind.car.repository.UserRepo;
import com.aravind.car.service.EmailService;
import com.aravind.car.serviceimpl.BookingServiceImpl;
import com.aravind.car.serviceimpl.PaymentServiceImpl;
import com.aravind.car.serviceimpl.RideServiceImpl;

@RestController
@RequestMapping("/booking")
@CrossOrigin("http://localhost:3000")
public class BookingController {

	@Autowired
	BookingServiceImpl service;

	@Autowired
	PaymentServiceImpl paymentService;

	@Autowired
	RideServiceImpl rideService;

	@Autowired
	BookingRepo bookingRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	EmailService emailService;

	static final String SUCCESS = "Success";
	static final String FAILURE = "Failure";

	@PostMapping
	public ResponseEntity<Response> insertBooking(@RequestBody Booking booking) {
		return service.addBooking(booking);
	}

	@GetMapping("{bookingId}")
	public Booking getBookingById(@PathVariable("bookingId") int id) {

		return service.getBooking(id);
	}

	@GetMapping("/all")
	public List<Booking> getBookings() {

		return service.getAllBooking();
	}

	@PutMapping
	public String updateBooking(@RequestBody Booking booking) {
		String msg = "";

		try {
			service.updateBooking(booking);
			msg = SUCCESS;
		} catch (Exception e) {
			msg = FAILURE;
		}

		return msg;
	}

	@DeleteMapping("{bookingId}")
	public String deleteBookById(@PathVariable("bookingId") int id) {
		String msg = "";

		try {
			service.deleteBooking(id);
			msg = SUCCESS;
		} catch (Exception e) {
			msg = FAILURE;
		}

		return msg;

	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Booking>> getBookingsByUserId(@PathVariable("userId") int userId) {
		List<Booking> bookings = service.getBookingsByUserId(userId);
		if (bookings != null && !bookings.isEmpty()) {
			return ResponseEntity.ok(bookings);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@PostMapping("/{bookingId}/payments")
	public ResponseEntity<String> addPaymentToBooking(@PathVariable("bookingId") int bookingId,
			@RequestBody Payment payment) {

		// Save the payment first
		String paymentResult = paymentService.addPayment(payment);
		if (!"Success".equals(paymentResult)) {
			return ResponseEntity.status(500).body("Failed to save payment");
		}

		// Update the booking with the payment
		String updateResult = service.updateBookingWithPayment(bookingId, payment);
		if ("Booking updated with payment".equals(updateResult)) {
			return ResponseEntity.ok(updateResult);
		} else {
			return ResponseEntity.status(404).body("Booking not found");
		}
	}

	@GetMapping("/{bookingId}/{userId}")
	public ResponseEntity<List<Booking>> getBookingsByBookingIdAndUserId(@PathVariable("bookingId") int bookingId,
			@PathVariable("userId") int userId) {
		List<Booking> bookings = service.getBookingsByBookingIdAndUserId(bookingId, userId);
		if (bookings != null && !bookings.isEmpty()) {
			return ResponseEntity.ok(bookings);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@PutMapping("/bookingStatus/{bookingId}")
	public ResponseEntity<String> updateBookingStatus(@PathVariable int bookingId,
			@RequestBody Map<String, String> statusUpdate) {
		String newStatus = statusUpdate.get("bookingStatus");

		// Fetch the booking details
		Booking booking = bookingRepo.findById(bookingId);
		if (booking == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found.");
		}

		// Update the booking status
		booking.setBookingStatus(newStatus);
		bookingRepo.save(booking);

		System.err.println("Booking Updated...");

		// Fetch the associated user
		User user = userRepo.findById(booking.getUser().getUserId());
		if (user != null) {
			try {
				if ("Approved".equals(newStatus)) {
					emailService.sendBookingApprovalEmail(user, booking);
				} else if ("Rejected".equals(newStatus)) {
					emailService.sendBookingRejectionEmail(user, booking);
				}
				System.err.println("try block cALL");
				return ResponseEntity.ok("Booking status updated and email sent.");
			} catch (Exception e) {
				System.err.println("catch block cALL");
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Failed to send email: " + e.getMessage());
			}
		} else {
			System.err.println("else block cALL");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
		}
	}

	@GetMapping("/track/{bookingId}/{userId}")
	public ResponseEntity<List<Booking>> getBookingsByIdAndUser(@PathVariable("bookingId") int bookingId,
			@PathVariable("userId") int userId) {
		List<Booking> bookings = service.getBookingsByBookingIdAndUserId(bookingId, userId);
		if (bookings != null && !bookings.isEmpty()) {
			return ResponseEntity.ok(bookings);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping("/forRide/{rideId}")
	public ResponseEntity<List<Booking>> getBookingsForRide(@PathVariable int rideId) {
		try {
			Ride ride = rideService.getRide(rideId); // Fetch the Ride entity using its ID
			if (ride == null) {
				return ResponseEntity.notFound().build(); // Handle case where Ride is not found
			}
			List<Booking> bookings = service.getBookingsByRide(ride);
			return ResponseEntity.ok(bookings);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

}
