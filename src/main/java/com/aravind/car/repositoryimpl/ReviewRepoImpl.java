package com.aravind.car.repositoryimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aravind.car.model.Review;
import com.aravind.car.repository.ReviewRepo;
import com.aravind.car.repository.RideRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ReviewRepoImpl implements ReviewRepo {

	@Autowired
	EntityManager entityManager;
	
	@Autowired
	RideRepo rideRepo;

	@Override
	public String save(Review review) {
		if (review != null) {
			entityManager.merge(review);
			System.out.println("review");
			return "Success";
		} else {
			return "Failure";
		}
	}

	@Override
	public String update(Review review) {
		if (review != null) {
			entityManager.merge(review);
			return "Success";
		} else {
			return "Failure";
		}

	}

	@Override
	public String delete(int reviewId) {
		Review id = entityManager.find(Review.class, reviewId);
		if (id != null) {
			entityManager.remove(id);
			return "Success";
		}
		return "Failure";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Review> findAllReviews() {
		String hql = "from Review";
		Query query = entityManager.createQuery(hql);
		return query.getResultList();
	}

	@Override
	public Review findById(int reviewId) {
		return entityManager.find(Review.class, reviewId);
	}

}
