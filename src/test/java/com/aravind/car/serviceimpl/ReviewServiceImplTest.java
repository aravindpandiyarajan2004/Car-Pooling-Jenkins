package com.aravind.car.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.aravind.car.model.Review;
import com.aravind.car.repository.ReviewRepo;
import com.aravind.car.repository.RideRepo;

public class ReviewServiceImplTest {

    @Mock
    private ReviewRepo reviewRepo;

    @Mock
    private RideRepo rideRepo; // RideRepo is injected but not used in the current implementation

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddReview() {
        Review review = new Review();
        when(reviewRepo.save(review)).thenReturn("Success");

        String result = reviewService.addReview(review);

        assertEquals("Success", result);
        verify(reviewRepo, times(1)).save(review);
    }

    @Test
    void testUpdateReview() {
        Review review = new Review();
        when(reviewRepo.update(review)).thenReturn("Success");

        String result = reviewService.updateReview(review);

        assertEquals("Success", result);
        verify(reviewRepo, times(1)).update(review);
    }

    @Test
    void testDeleteReview() {
        int reviewId = 1;
        when(reviewRepo.delete(reviewId)).thenReturn("Success");

        String result = reviewService.deleteReview(reviewId);

        assertEquals("Success", result);
        verify(reviewRepo, times(1)).delete(reviewId);
    }

    @Test
    void testGetAllReview() {
        Review review1 = new Review();
        Review review2 = new Review();
        when(reviewRepo.findAllReviews()).thenReturn(Arrays.asList(review1, review2));

        List<Review> result = reviewService.getAllReview();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(reviewRepo, times(1)).findAllReviews();
    }

    @Test
    void testGetReview() {
        int reviewId = 1;
        Review review = new Review();
        when(reviewRepo.findById(reviewId)).thenReturn(review);

        Review result = reviewService.getReview(reviewId);

        assertNotNull(result);
        assertEquals(review, result);
        verify(reviewRepo, times(1)).findById(reviewId);
    }
}
