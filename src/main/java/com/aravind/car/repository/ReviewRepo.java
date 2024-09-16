package com.aravind.car.repository;

import java.util.List;

import com.aravind.car.model.Review;

public interface ReviewRepo {
	public String save(Review review);

	public String update(Review review);

	public String delete(int reviewId);

	public List<Review> findAllReviews();

	public Review findById(int reviewId);

}
