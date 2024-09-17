package com.aravind.car.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.aravind.car.model.Booking;
import com.aravind.car.model.Payment;
import com.aravind.car.model.Ride;
import com.aravind.car.repository.BookingRepo;

public class BookingServiceImplTest {

    @Mock
    private BookingRepo bookingRepo;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

 

    @Test
    void testUpdateBooking() {
        Booking booking = new Booking();
        when(bookingRepo.update(booking)).thenReturn("Success");

        String result = bookingService.updateBooking(booking);

        assertEquals("Success", result);
        verify(bookingRepo, times(1)).update(booking);
    }

    @Test
    void testDeleteBooking() {
        int bookingId = 1;
        when(bookingRepo.delete(bookingId)).thenReturn("Success");

        String result = bookingService.deleteBooking(bookingId);

        assertEquals("Success", result);
        verify(bookingRepo, times(1)).delete(bookingId);
    }

    @Test
    void testGetAllBooking() {
        Booking booking1 = new Booking();
        Booking booking2 = new Booking();
        when(bookingRepo.findAllBookings()).thenReturn(Arrays.asList(booking1, booking2));

        var result = bookingService.getAllBooking();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(bookingRepo, times(1)).findAllBookings();
    }

    @Test
    void testGetBooking() {
        int bookingId = 1;
        Booking booking = new Booking();
        when(bookingRepo.findById(bookingId)).thenReturn(booking);

        Booking result = bookingService.getBooking(bookingId);

        assertNotNull(result);
        assertEquals(booking, result);
        verify(bookingRepo, times(1)).findById(bookingId);
    }

    @Test
    void testGetPendingBookings() {
        Booking booking = new Booking();
        when(bookingRepo.findByBookingStatus("Pending")).thenReturn(Arrays.asList(booking));

        var result = bookingService.getPendingBookings();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(bookingRepo, times(1)).findByBookingStatus("Pending");
    }

    @Test
    void testGetBookingsByUserId() {
        int userId = 1;
        Booking booking = new Booking();
        when(bookingRepo.findBookingByUSerId(userId)).thenReturn(Arrays.asList(booking));

        var result = bookingService.getBookingsByUserId(userId);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(bookingRepo, times(1)).findBookingByUSerId(userId);
    }

    @Test
    void testUpdateBookingWithPayment() {
        int bookingId = 1;
        Payment payment = new Payment();
        when(bookingRepo.updateBookingWithPayment(bookingId, payment)).thenReturn("Success");

        String result = bookingService.updateBookingWithPayment(bookingId, payment);

        assertEquals("Success", result);
        verify(bookingRepo, times(1)).updateBookingWithPayment(bookingId, payment);
    }

    @Test
    void testUpdateBookingStatus() {
        int bookingId = 1;
        String status = "Confirmed";
        when(bookingRepo.updateStatus(bookingId, status)).thenReturn(true);

        boolean result = bookingService.updateBookingStatus(bookingId, status);

        assertTrue(result);
        verify(bookingRepo, times(1)).updateStatus(bookingId, status);
    }

    @Test
    void testGetBookingsByBookingIdAndUserId() {
        int bookingId = 1;
        int userId = 1;
        Booking booking = new Booking();
        when(bookingRepo.findByBookingIdAndUserId(bookingId, userId)).thenReturn(Arrays.asList(booking));

        var result = bookingService.getBookingsByBookingIdAndUserId(bookingId, userId);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(bookingRepo, times(1)).findByBookingIdAndUserId(bookingId, userId);
    }

    @Test
    void testGetBookingsByBookingIdOrUserId() {
        int bookingId = 1;
        int userId = 1;
        Booking booking = new Booking();
        when(bookingRepo.findByBookingIdOrUserId(bookingId, userId)).thenReturn(Arrays.asList(booking));

        var result = bookingService.getBookingsByBookingIdOrUserId(bookingId, userId);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(bookingRepo, times(1)).findByBookingIdOrUserId(bookingId, userId);
    }

    @Test
    void testGetBookingsByRide() {
        Ride ride = new Ride();
        Booking booking = new Booking();
        when(bookingRepo.findByRide(ride)).thenReturn(Arrays.asList(booking));

        var result = bookingService.getBookingsByRide(ride);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(bookingRepo, times(1)).findByRide(ride);
    }
}
