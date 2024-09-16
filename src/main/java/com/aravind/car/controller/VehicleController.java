package com.aravind.car.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aravind.car.model.User;
import com.aravind.car.model.Vehicle;
import com.aravind.car.serviceimpl.VehicleServiceImpl;

@RestController
@RequestMapping("/vehicle")
@CrossOrigin("http://localhost:3000")
public class VehicleController {

	@Autowired
	private VehicleServiceImpl service;

	private static final String SUCCESS = "Success";
	private static final String FAILURE = "Failure";

	@PostMapping
	public String insertVehicle(@RequestParam("carImage") MultipartFile carImage,
			@RequestParam("vehicleName") String vehicleName, @RequestParam("model") String model,
			@RequestParam("numberPlate") String numberPlate, @RequestParam("seatingCapacity") int seatingCapacity,
			@RequestParam("license") MultipartFile license, @RequestParam("rcBook") MultipartFile rcBook,
			@RequestParam("userId") int userId) {

		try {
			Vehicle vehicle = new Vehicle();
			vehicle.setCarImage(carImage.getBytes());
			vehicle.setVehicleName(vehicleName);
			vehicle.setModel(model);
			vehicle.setNumberPlate(numberPlate);
			vehicle.setSeatingCapacity(seatingCapacity);
			vehicle.setLicense(license.getBytes());
			vehicle.setRcBook(rcBook.getBytes());

			User user = new User();
			user.setUserId(userId);
			vehicle.setUser(user);

			service.addVehicle(vehicle);

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return FAILURE;
		}
	}

	@GetMapping("{vehicleId}")
	public Vehicle getVehicleById(@PathVariable("vehicleId") int id) {
		return service.getVehicle(id);
	}

	@GetMapping("/all")
	public List<Vehicle> getVehicles() {
		return service.getAllVehicle();
	}

	@PutMapping
	public String updateVehicle(@RequestParam("vehicleId") int vehicleId,
			@RequestParam(value = "carImage", required = false) MultipartFile carImage,
			@RequestParam(value = "vehicleName", required = false) String vehicleName,
			@RequestParam(value = "model", required = false) String model,
			@RequestParam(value = "numberPlate", required = false) String numberPlate,
			@RequestParam(value = "seatingCapacity", required = false) Integer seatingCapacity,
			@RequestParam(value = "license", required = false) MultipartFile license,
			@RequestParam(value = "rcBook", required = false) MultipartFile rcBook) {
		String msg = "";
		try {

			Vehicle vehicle = service.getVehicle(vehicleId);

			if (carImage != null) {
				vehicle.setCarImage(carImage.getBytes());
			}
			if (vehicleName != null) {
				vehicle.setVehicleName(vehicleName);
			}
			if (model != null) {
				vehicle.setModel(model);
			}
			if (numberPlate != null) {
				vehicle.setNumberPlate(numberPlate);
			}
			if (seatingCapacity != null) {
				vehicle.setSeatingCapacity(seatingCapacity);
			}
			if (license != null) {
				vehicle.setLicense(license.getBytes());
			}
			if (rcBook != null) {
				vehicle.setRcBook(rcBook.getBytes());
			}

			service.updateVehicle(vehicle);
			msg = SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			msg = FAILURE;
		}
		return msg;
	}

	@DeleteMapping("{vehicleId}")
	public String deleteVehicleById(@PathVariable("vehicleId") int id) {
		try {
			service.deleteVehicle(id);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return FAILURE;
		}
	}

	@GetMapping("/byUser")
	public List<Vehicle> getVehiclesByUserId(@RequestParam("userId") int userId) {
		return service.getVehiclesByUserId(userId);
	}
}
