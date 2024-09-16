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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aravind.car.model.Review;
import com.aravind.car.model.Ride;
import com.aravind.car.repository.RideRepo;
import com.aravind.car.serviceimpl.ReviewServiceImpl;

@RestController
@RequestMapping("/review")
@CrossOrigin("http://localhost:3000")
public class ReviewController {

	@Autowired
	ReviewServiceImpl service;
	
	@Autowired 
	RideRepo rideRepo;
	
	

	static final String SUCCESS = "Success";
	static final String FAILURE = "Failure";

//	@PostMapping
//	public String insertReview(@RequestBody Review review) {
//		
//		String msg = "";
//
//		try {
//			service.addReview(review);
//			
//			System.out.print(review);
//			msg = SUCCESS;
//		} catch (Exception e) {
//			msg = FAILURE;
//		}
//
//		return msg;
//	}

	@PostMapping
	public String insertReview(@RequestParam("rating") int rating, @RequestParam("review") String review,
			@RequestParam("rideId") int rideId) {
		String msg = "";

		try {
			// Create a new Review object using the request parameters
			Review reviewObj = new Review();
			//reviewObj.setRating((int) rating);
			reviewObj.setRating(rideId);
			reviewObj.setReview(review);

			// Assuming Ride is an entity that can be fetched by its ID
			Ride ride = rideRepo.findById(rideId); // Make sure this method exists in your service
			reviewObj.setRide(ride);

			service.addReview(reviewObj);

			System.out.println(reviewObj);
			msg = SUCCESS;
		} catch (Exception e) {
			msg = FAILURE;
		}

		return msg;
	}

	@GetMapping("{reviewId}")
	public Review getReviewById(@PathVariable("reviewId") int id) {

		return service.getReview(id);
	}

	@GetMapping("/all")
	public List<Review> getReviews() {

		return service.getAllReview();
	}

	@PutMapping
	public String updateReview(@RequestBody Review review) {
		String msg = "";

		try {
			service.updateReview(review);
			msg = SUCCESS;
		} catch (Exception e) {
			msg = FAILURE;
		}

		return msg;
	}

	@DeleteMapping("{reviewId}")
	public String deleteReviewById(@PathVariable("reviewId") int id) {
		String msg = "";

		try {
			service.deleteReview(id);
			msg = SUCCESS;
		} catch (Exception e) {
			msg = FAILURE;
		}

		return msg;

	}

}
