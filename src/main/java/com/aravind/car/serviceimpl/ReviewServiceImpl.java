package com.aravind.car.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aravind.car.model.Review;
import com.aravind.car.repository.ReviewRepo;
import com.aravind.car.repository.RideRepo;
import com.aravind.car.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewRepo reviewRepo;
	
	@Autowired
	RideRepo rideRepo;

	@Override
	public String addReview(Review review) {
	return reviewRepo.save(review);
	}

	@Override
	public String updateReview(Review review) {
		return reviewRepo.update(review);
	}

	@Override
	public String deleteReview(int reviewId) {
		return reviewRepo.delete(reviewId);
	}

	@Override
	public List<Review> getAllReview() {
		return reviewRepo.findAllReviews();
	}

	@Override
	public Review getReview(int reviewId) {
		return reviewRepo.findById(reviewId);
	}

	

}
