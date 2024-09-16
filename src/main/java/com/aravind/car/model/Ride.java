package com.aravind.car.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ride_tbl")
public class Ride {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rideId;

	@Column
	private String startPoint;

	@Column
	private String endPoint;

	@Column
	private String departureTime;

	@Column
	private String arrivalTime;

	@Column
	private Date rideDate;

	@Column
	private int distance;

	@Column
	private double pricePerPerson;

	@Column
	private int availableSeats;

	@ManyToOne
	@JoinColumn(name = "user_Id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "vehicle_Id")
	private Vehicle vehicle;
	

	public Ride() {
		super();
	}

	public Ride(int rideId, String startPoint, String endPoint, String departureTime, String arrivalTime, Date rideDate,
			int distance, double pricePerPerson, int availableSeats, User user, Vehicle vehicle) {
		super();
		this.rideId = rideId;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.rideDate = rideDate;
		this.distance = distance;
		this.pricePerPerson = pricePerPerson;
		this.availableSeats = availableSeats;
		this.user = user;
		this.vehicle = vehicle;
	}

	public int getRideId() {
		return rideId;
	}

	public void setRideId(int rideId) {
		this.rideId = rideId;
	}

	public String getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Date getRideDate() {
		return rideDate;
	}

	public void setRideDate(Date rideDate) {
		this.rideDate = rideDate;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public double getPricePerPerson() {
		return pricePerPerson;
	}

	public void setPricePerPerson(double pricePerPerson) {
		this.pricePerPerson = pricePerPerson;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public String toString() {
		return "Ride [rideId=" + rideId + ", startPoint=" + startPoint + ", endPoint=" + endPoint + ", departureTime="
				+ departureTime + ", arrivalTime=" + arrivalTime + ", rideDate=" + rideDate + ", distance=" + distance
				+ ", pricePerPerson=" + pricePerPerson + ", availableSeats=" + availableSeats + ", user=" + user
				+ ", vehicle=" + vehicle + "]";
	}

}
