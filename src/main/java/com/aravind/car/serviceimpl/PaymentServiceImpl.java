package com.aravind.car.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aravind.car.model.Payment;
import com.aravind.car.repository.PaymentRepo;
import com.aravind.car.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentRepo paymentRepo;

	@Override
	public String addPayment(Payment payment) {
		return paymentRepo.save(payment);
	}

	@Override
	public String updatePayment(Payment payment) {
		return paymentRepo.update(payment);
	}

	@Override
	public String deletePayment(int payId) {
		return paymentRepo.delete(payId);
	}

	@Override
	public List<Payment> getAllPayment() {
		return paymentRepo.findAllPayments();
	}

	@Override
	public Payment getPayment(int payId) {
		return paymentRepo.findById(payId);
	}

	public Optional<Payment> getPaymentByUserId(int userId) {
		return paymentRepo.findByUserId(userId);
	}

	public String updatePaymentDetails(int payId, Payment updateRequest) {
		return paymentRepo.updatePaymentDetails(payId, updateRequest);

	}

}
