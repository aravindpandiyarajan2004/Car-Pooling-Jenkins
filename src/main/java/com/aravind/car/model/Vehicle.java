package com.aravind.car.model;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehicle_tbl")
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vehicleId;

	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] carImage;

	@Column
	private String vehicleName;

	@Column
	private String model;

	@Column
	private String numberPlate;

	@Column
	private int seatingCapacity;

	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] license;

	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] rcBook;

	@ManyToOne
	@JoinColumn(name = "user_Id")
	private User user;

	public Vehicle() {
		super();
	}

	public Vehicle(int vehicleId, byte[] carImage, String vehicleName, String model, String numberPlate,
			int seatingCapacity, byte[] license, byte[] rcBook, User user) {
		super();
		this.vehicleId = vehicleId;
		this.carImage = carImage;
		this.vehicleName = vehicleName;
		this.model = model;
		this.numberPlate = numberPlate;
		this.seatingCapacity = seatingCapacity;
		this.license = license;
		this.rcBook = rcBook;
		this.user = user;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public byte[] getCarImage() {
		return carImage;
	}

	public void setCarImage(byte[] carImage) {
		this.carImage = carImage;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getNumberPlate() {
		return numberPlate;
	}

	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}

	public int getSeatingCapacity() {
		return seatingCapacity;
	}

	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}

	public byte[] getLicense() {
		return license;
	}

	public void setLicense(byte[] license) {
		this.license = license;
	}

	public byte[] getRcBook() {
		return rcBook;
	}

	public void setRcBook(byte[] rcBook) {
		this.rcBook = rcBook;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", carImage=" + Arrays.toString(carImage) + ", vehicleName="
				+ vehicleName + ", model=" + model + ", numberPlate=" + numberPlate + ", seatingCapacity="
				+ seatingCapacity + ", license=" + Arrays.toString(license) + ", rcBook=" + Arrays.toString(rcBook)
				+ ", user=" + user + "]";
	}

}
