package com.aravind.car.repository;

import java.util.List;
import java.util.Optional;

import com.aravind.car.model.Payment;

public interface PaymentRepo {
	public String save(Payment payment);

	public String update(Payment payment);

	public String delete(int payId);

	public List<Payment> findAllPayments();

	public Payment findById(int payId);

	public Optional<Payment> findByUserId(int userId);

	public String updatePaymentDetails(int payId, Payment updateRequest);

}
