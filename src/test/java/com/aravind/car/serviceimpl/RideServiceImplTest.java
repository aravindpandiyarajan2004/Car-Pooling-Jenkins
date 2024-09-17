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

import com.aravind.car.model.Ride;
import com.aravind.car.repository.RideRepo;
import com.aravind.car.service.RideService;

public class RideServiceImplTest {

    @Mock
    private RideRepo rideRepo;

    @InjectMocks
    private RideServiceImpl rideService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddRide() {
        Ride ride = new Ride();
        when(rideRepo.save(ride)).thenReturn("Success");

        String result = rideService.addRide(ride);

        assertEquals("Success", result);
        verify(rideRepo, times(1)).save(ride);
    }

    @Test
    void testUpdateRide() {
        Ride ride = new Ride();
        when(rideRepo.update(ride)).thenReturn("Success");

        String result = rideService.updateRide(ride);

        assertEquals("Success", result);
        verify(rideRepo, times(1)).update(ride);
    }

    @Test
    void testDeleteRide() {
        int rideId = 1;
        when(rideRepo.delete(rideId)).thenReturn("Success");

        String result = rideService.deleteRide(rideId);

        assertEquals("Success", result);
        verify(rideRepo, times(1)).delete(rideId);
    }

    @Test
    void testGetAllRide() {
        Ride ride1 = new Ride();
        Ride ride2 = new Ride();
        when(rideRepo.findAllRides()).thenReturn(Arrays.asList(ride1, ride2));

        List<Ride> result = rideService.getAllRide();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(rideRepo, times(1)).findAllRides();
    }

    @Test
    void testGetRide() {
        int rideId = 1;
        Ride ride = new Ride();
        when(rideRepo.findById(rideId)).thenReturn(ride);

        Ride result = rideService.getRide(rideId);

        assertNotNull(result);
        assertEquals(ride, result);
        verify(rideRepo, times(1)).findById(rideId);
    }

    @Test
    void testGetRidesByUserId() {
        int userId = 1;
        Ride ride = new Ride();
        when(rideRepo.findByUserId(userId)).thenReturn(Arrays.asList(ride));

        List<Ride> result = rideService.getRidesByUserId(userId);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(rideRepo, times(1)).findByUserId(userId);
    }

    @Test
    void testGetDistinctStartPoints() {
        List<String> startPoints = Arrays.asList("Point A", "Point B");
        when(rideRepo.findDistinctStartPoints()).thenReturn(startPoints);

        List<String> result = rideService.getDistinctStartPoints();

        assertNotNull(result);
        assertEquals(startPoints, result);
        verify(rideRepo, times(1)).findDistinctStartPoints();
    }

    @Test
    void testGetDistinctEndPoints() {
        List<String> endPoints = Arrays.asList("Point X", "Point Y");
        when(rideRepo.findDistinctEndPoints()).thenReturn(endPoints);

        List<String> result = rideService.getDistinctEndPoints();

        assertNotNull(result);
        assertEquals(endPoints, result);
        verify(rideRepo, times(1)).findDistinctEndPoints();
    }
}
