package com.aravind.car.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.aravind.car.model.Booking;
import com.aravind.car.model.Payment;
import com.aravind.car.model.Response;
import com.aravind.car.model.Ride;

public interface BookingRepo {
//	public String save(Booking booking);

	public String update(Booking booking);

	public String delete(int bookingId);

	public List<Booking> findAllBookings();

	public Booking findById(int BookingId);

	public List<Booking> findByBookingStatus(String string);

	public List<Booking> findBookingByUSerId(int userId);
	
	 public String updateBookingWithPayment(int bookingId, Payment payment);
	 
	 public boolean updateStatus(int bookingId, String bookingStatus);

	public ResponseEntity<Response> save(Booking booking);

	public List<Booking> findByBookingIdAndUserId(int bookingId, int userId);

	public List<Booking> findByBookingIdOrUserId(int bookingId, int userId);

	public List<Booking> findByRide(Ride ride);

}
