package com.aravind.car.repositoryimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.aravind.car.model.Booking;
import com.aravind.car.model.Payment;
import com.aravind.car.model.Response;
import com.aravind.car.model.Ride;
import com.aravind.car.repository.BookingRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class BookingRepoImpl implements BookingRepo {

	@Autowired
	EntityManager entityManager;

	@Autowired
	RideRepoImpl rideRepo;

	@Override
	public String update(Booking booking) {
		if (booking != null) {
			entityManager.merge(booking);
			return "Success";
		} else {
			return "Failure";
		}

	}

	@Override
	public String delete(int bookingId) {
		Booking id = entityManager.find(Booking.class, bookingId);
		if (id != null) {
			entityManager.remove(id);
			return "Success";
		}
		return "Failure";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Booking> findAllBookings() {
		String hql = "from Booking";
		Query query = entityManager.createQuery(hql);
		return query.getResultList();
	}

	@Override
	public Booking findById(int bookingId) {
		return entityManager.find(Booking.class, bookingId);
	}

	@Override
	public List<Booking> findByBookingStatus(String status) {
		String jpql = "SELECT b FROM Booking b WHERE b.bookingStatus = :status";
		TypedQuery<Booking> query = entityManager.createQuery(jpql, Booking.class);
		query.setParameter("status", status);
		return query.getResultList();
	}

	@Override
	public List<Booking> findBookingByUSerId(int userId) {
		String jpql = "SELECT b FROM Booking b WHERE b.user.userId = :userId";
		TypedQuery<Booking> query = entityManager.createQuery(jpql, Booking.class);
		query.setParameter("userId", userId);
		return query.getResultList();
	}

	@Override
	public String updateBookingWithPayment(int bookingId, Payment payment) {
		if (payment == null) {
			return "Failure: Payment object is null";
		}

		Booking booking = entityManager.find(Booking.class, bookingId);
		if (booking == null) {
			return "Failure: Booking not found";
		}

		booking.setPayment(payment);
		booking.setBookingStatus("Confirmed"); // Update status if needed
		entityManager.merge(booking);

		return "Booking updated with payment";
	}

	public boolean updateStatus(int bookingId, String bookingStatus) {
		try {
			Booking booking = entityManager.find(Booking.class, bookingId);
			if (booking != null) {
				booking.setBookingStatus(bookingStatus);
				entityManager.persist(booking); // Update the entity
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace(); // Handle exceptions properly
			return false;
		}
	}

	@Override
	public ResponseEntity<Response> save(Booking booking) {
		if (booking == null) {
			return new ResponseEntity<>(new Response(400, "Failure: Booking object is null"), HttpStatus.BAD_REQUEST);
		}

		// Retrieve ride entity to check availability
		Ride ride = entityManager.find(Ride.class, booking.getRide().getRideId());
		if (ride == null) {
			return new ResponseEntity<>(new Response(404, "Failure: Ride not found"), HttpStatus.NOT_FOUND);
		}

		// Check if the booking user is the same as the ride user
		if (booking.getUser().getUserId() == (ride.getUser().getUserId())) {
			return new ResponseEntity<>(new Response(403, "Failure: Cannot book own ride"), HttpStatus.FORBIDDEN);
		}

		int requestedSeats = booking.getRequestedSeats();

		// Check seat availability
		if (ride.getAvailableSeats() < requestedSeats) {
			return new ResponseEntity<>(new Response(409, "Failure: Insufficient seats available"),
					HttpStatus.CONFLICT);
		}

		// Update the available seats
		ride.setAvailableSeats(ride.getAvailableSeats() - requestedSeats);

		// Calculate the booking amount
		int amount = requestedSeats * (int) ride.getPricePerPerson();

		// Create and persist payment
		Payment payment = new Payment();
		payment.setAmount(amount);
		payment.setUser(booking.getUser());
		payment.setStatus("Pending");
		entityManager.merge(payment);

		// Associate payment with booking
		booking.setPayment(payment);

		// Persist the updated ride and booking
		entityManager.merge(ride);
		entityManager.persist(booking);

		return new ResponseEntity<>(new Response(200, "Booking successful"), HttpStatus.OK);
	}

	public List<Booking> findByBookingIdAndUserId(int bookingId, int userId) {
		String jpql = "SELECT b FROM Booking b WHERE b.bookingId = :bookingId AND b.user.userId = :userId";
		TypedQuery<Booking> query = entityManager.createQuery(jpql, Booking.class);
		query.setParameter("bookingId", bookingId);
		query.setParameter("userId", userId);
		return query.getResultList();
	}

	@Override
	public List<Booking> findByBookingIdOrUserId(int bookingId, int userId) {
		StringBuilder hql = new StringBuilder("FROM Booking b WHERE ");
		if (bookingId > 0) {
			hql.append("b.bookingId = :bookingId");
		}
		if (userId > 0) {
			if (bookingId > 0) {
				hql.append(" OR ");
			}
			hql.append("b.user.userId = :userId");
		}

		TypedQuery<Booking> query = entityManager.createQuery(hql.toString(), Booking.class);
		if (bookingId > 0) {
			query.setParameter("bookingId", bookingId);
		}
		if (userId > 0) {
			query.setParameter("userId", userId);
		}
		return query.getResultList();
	}

	@Override
	public List<Booking> findByRide(Ride ride) {
		String jpql = "SELECT b FROM Booking b WHERE b.ride = :ride";
		TypedQuery<Booking> query = entityManager.createQuery(jpql, Booking.class);
		query.setParameter("ride", ride);
		return query.getResultList();
	}
}
