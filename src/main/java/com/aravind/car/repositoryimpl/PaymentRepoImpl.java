package com.aravind.car.repositoryimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aravind.car.repository.PaymentRepo;
import com.aravind.car.model.Payment;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class PaymentRepoImpl implements PaymentRepo {

	@Autowired
	EntityManager entityManager;

	@Override
	public String save(Payment payment) {
		if (payment != null) {
			entityManager.merge(payment);
			return "Success";
		} else {
			return "Failure";
		}
	}

	@Override
	public String update(Payment payment) {
		if (payment != null) {
			entityManager.merge(payment);
			return "Success";
		} else {
			return "Failure";
		}

	}

	@Override
	public String delete(int payId) {
		Payment id = entityManager.find(Payment.class, payId);
		if (id != null) {
			entityManager.remove(id);
			return "Success";
		}
		return "Failure";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Payment> findAllPayments() {
		String hql = "from Payment";
		Query query = entityManager.createQuery(hql);
		return query.getResultList();
	}

	@Override
	public Payment findById(int payId) {
		return entityManager.find(Payment.class, payId);
	}

	@Override
	public Optional<Payment> findByUserId(int userId) {
		TypedQuery<Payment> query = entityManager.createQuery("SELECT p FROM Payment p WHERE p.user.userId = :userId",
				Payment.class);
		query.setParameter("userId", userId);
		Payment payment = query.getResultStream().findFirst().orElse(null);
		return Optional.ofNullable(payment);
	}

	@Override
	public String updatePaymentDetails(int payId, Payment updateRequest) {
		
		
		System.err.println(updateRequest);
		try {
			Payment existingPayment = entityManager.find(Payment.class, payId);
			if (existingPayment != null) {
				if (updateRequest.getPayMethod() != null) {
					existingPayment.setPayMethod(updateRequest.getPayMethod());
				}
				if (updateRequest.getStatus() != null) {
					existingPayment.setStatus(updateRequest.getStatus());
				}
				if (updateRequest.getPayDate() != null) {
					existingPayment.setPayDate(updateRequest.getPayDate());
				}
				
				System.err.println(existingPayment.getPayId());
				entityManager.merge(existingPayment); // Update the entity
				return "Success";
			} else {
				return "Payment not found";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Failure";
		}
	}
}
