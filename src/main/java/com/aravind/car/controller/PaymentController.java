package com.aravind.car.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aravind.car.model.Payment;
import com.aravind.car.serviceimpl.PaymentServiceImpl;

@RestController
@RequestMapping("/payment")
@CrossOrigin("http://localhost:3000")
public class PaymentController {

	@Autowired
	PaymentServiceImpl service;

	static final String SUCCESS = "Success";
	static final String FAILURE = "Failure";

	@PostMapping
	public String insertPayment(@RequestBody Payment payment) {

		String msg = "";

		try {
			service.addPayment(payment);
			msg = SUCCESS;
		} catch (Exception e) {
			msg = FAILURE;
		}

		return msg;
	}

	@GetMapping("{payId}")
	public Payment getPaymentById(@PathVariable("payId") int id) {

		return service.getPayment(id);
	}

	@GetMapping("/all")
	public List<Payment> getPayments() {

		return service.getAllPayment();
	}

	@PutMapping
	public String updatePayment(@RequestBody Payment payment) {
		String msg = "";

		try {
			service.updatePayment(payment);
			msg = SUCCESS;
		} catch (Exception e) {
			msg = FAILURE;
		}

		return msg;
	}

	@DeleteMapping("{payId}")
	public String deletePaymentById(@PathVariable("payId") int id) {
		String msg = "";

		try {
			service.deletePayment(id);
			msg = SUCCESS;
		} catch (Exception e) {
			msg = FAILURE;
		}

		return msg;

	}
//
//	@GetMapping("{userId}")
//	public Optional<Payment> getPaymentByUserId(@PathVariable int userId) {
//		return service.getPaymentByUserId(userId);
//	}

	@PutMapping("/{payId}")
	public String updatePayment(@PathVariable("payId") int payId, @RequestBody Payment updateRequest) {
		String msg = "";
		try {
			service.updatePaymentDetails(payId, updateRequest);
			msg = SUCCESS;
		} catch (Exception e) {
			msg = FAILURE;
		}
		return msg;
	}

}
