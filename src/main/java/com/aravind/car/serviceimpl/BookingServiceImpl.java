package com.aravind.car.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aravind.car.model.Booking;
import com.aravind.car.model.Payment;
import com.aravind.car.model.Response;
import com.aravind.car.model.Ride;
import com.aravind.car.repository.BookingRepo;
import com.aravind.car.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingRepo bookingRepo;

	@Override
	public ResponseEntity<Response> addBooking(Booking booking) {
		return bookingRepo.save(booking);
	}

	@Override
	public String updateBooking(Booking booking) {
		return bookingRepo.update(booking);
	}


	@Override
	public String deleteBooking(int bookingId) {
		return bookingRepo.delete(bookingId);
	}

	@Override
	public List<Booking> getAllBooking() {
		return bookingRepo.findAllBookings();
	}

	@Override
	public Booking getBooking(int bookingId) {
		return bookingRepo.findById(bookingId);
	}

	@Override
	public List<Booking> getPendingBookings() {
		return bookingRepo.findByBookingStatus("Pending");
	}

	public List<Booking> getBookingsByUserId(int userId) {

		return bookingRepo.findBookingByUSerId(userId);
	}

	@Override
	public String updateBookingWithPayment(int bookingId, Payment payment) {
		return bookingRepo.updateBookingWithPayment(bookingId, payment);
	}

	@Override
	public boolean updateBookingStatus(int bookingId, String bookingStatus) {
		return bookingRepo.updateStatus(bookingId, bookingStatus);
	}

	@Override
	public List<Booking> getBookingsByBookingIdAndUserId(int bookingId, int userId) {
		return bookingRepo.findByBookingIdAndUserId(bookingId, userId);
	}

	public List<Booking> getBookingsByBookingIdOrUserId(int bookingId, int userId) {
		return bookingRepo.findByBookingIdOrUserId(bookingId, userId);
	}


	@Override
	public List<Booking> getBookingsByRide(Ride ride) {
		return bookingRepo.findByRide(ride);
	}

}
