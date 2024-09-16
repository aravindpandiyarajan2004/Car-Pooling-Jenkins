package com.aravind.car.service;

import java.util.List;
import java.util.Optional;

import com.aravind.car.model.Payment;

public interface PaymentService {
	public String addPayment(Payment payment);

	public String updatePayment(Payment payment);

	public String deletePayment(int payId);

	public List<Payment> getAllPayment();

	public Payment getPayment(int payId);

	Optional<Payment> getPaymentByUserId(int userId);

	public String updatePaymentDetails(int payId, Payment updateRequest);

}
