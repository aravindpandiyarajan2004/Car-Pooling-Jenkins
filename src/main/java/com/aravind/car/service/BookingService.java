package com.aravind.car.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.aravind.car.model.Booking;
import com.aravind.car.model.Payment;
import com.aravind.car.model.Response;
import com.aravind.car.model.Ride;

public interface BookingService {
	public ResponseEntity<Response> addBooking(Booking booking);

	public String updateBooking(Booking booking);

	public String deleteBooking(int bookingId);

	public List<Booking> getAllBooking();

	public Booking getBooking(int bookingId);

	public List<Booking> getPendingBookings();

	public String updateBookingWithPayment(int bookingId, Payment payment);

	public boolean updateBookingStatus(int bookingId, String bookingStatus);

	public List<Booking> getBookingsByBookingIdAndUserId(int bookingId, int userId);
	
	public List<Booking> getBookingsByBookingIdOrUserId(int bookingId, int userId);
	
	public List<Booking> getBookingsByRide(Ride ride);

}
