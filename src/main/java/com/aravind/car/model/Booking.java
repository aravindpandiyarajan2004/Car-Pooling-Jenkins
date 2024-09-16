package com.aravind.car.model;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "booking_tbl")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;

	@Column
	private int requestedSeats;

	@Column
	private String bookingDate;

//	@Column
//	private double amount;

	@Column
	@ColumnDefault("'Pending'")
	private String bookingStatus;

	@ManyToOne
	@JoinColumn(name = "ride_Id")
	private Ride ride;

	@ManyToOne
	@JoinColumn(name = "user_Id")
	private User user;

	@OneToOne
	@JoinColumn(name = "pay_Id")
	private Payment payment;

	public Booking() {
		super();
	}

	public Booking(int bookingId, int requestedSeats, String bookingDate, String bookingStatus, Ride ride, User user,
			Payment payment) {
		super();
		this.bookingId = bookingId;
		this.requestedSeats = requestedSeats;
		this.bookingDate = bookingDate;
		this.bookingStatus = bookingStatus;
		this.ride = ride;
		this.user = user;
		this.payment = payment;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getRequestedSeats() {
		return requestedSeats;
	}

	public void setRequestedSeats(int requestedSeats) {
		this.requestedSeats = requestedSeats;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public Ride getRide() {
		return ride;
	}

	public void setRide(Ride ride) {
		this.ride = ride;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", requestedSeats=" + requestedSeats + ", bookingDate=" + bookingDate
				+ ", bookingStatus=" + bookingStatus + ", ride=" + ride + ", user=" + user + ", payment=" + payment
				+ "]";
	}

}
