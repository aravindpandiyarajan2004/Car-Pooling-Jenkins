package com.aravind.car.service;

import java.util.List;

import com.aravind.car.model.Review;

public interface ReviewService {

	public String addReview(Review review);

	public String updateReview(Review review);

	public String deleteReview(int reviewId);

	public List<Review> getAllReview();

	public Review getReview(int reviewId);


}
