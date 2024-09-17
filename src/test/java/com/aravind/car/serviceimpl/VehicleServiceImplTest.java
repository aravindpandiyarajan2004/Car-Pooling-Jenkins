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

import com.aravind.car.model.Vehicle;
import com.aravind.car.repository.VehicleRepo;

public class VehicleServiceImplTest {

    @Mock
    private VehicleRepo vehicleRepo;

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddVehicle() {
        Vehicle vehicle = new Vehicle();
        when(vehicleRepo.save(vehicle)).thenReturn("Success");

        String result = vehicleService.addVehicle(vehicle);

        assertEquals("Success", result);
        verify(vehicleRepo, times(1)).save(vehicle);
    }

    @Test
    void testUpdateVehicle() {
        Vehicle vehicle = new Vehicle();
        when(vehicleRepo.update(vehicle)).thenReturn("Success");

        String result = vehicleService.updateVehicle(vehicle);

        assertEquals("Success", result);
        verify(vehicleRepo, times(1)).update(vehicle);
    }

    @Test
    void testDeleteVehicle() {
        int vehicleId = 1;
        when(vehicleRepo.delete(vehicleId)).thenReturn("Success");

        String result = vehicleService.deleteVehicle(vehicleId);

        assertEquals("Success", result);
        verify(vehicleRepo, times(1)).delete(vehicleId);
    }

    @Test
    void testGetAllVehicle() {
        Vehicle vehicle1 = new Vehicle();
        Vehicle vehicle2 = new Vehicle();
        when(vehicleRepo.findAllVehicles()).thenReturn(Arrays.asList(vehicle1, vehicle2));

        List<Vehicle> result = vehicleService.getAllVehicle();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(vehicleRepo, times(1)).findAllVehicles();
    }

    @Test
    void testGetVehicle() {
        int vehicleId = 1;
        Vehicle vehicle = new Vehicle();
        when(vehicleRepo.findById(vehicleId)).thenReturn(vehicle);

        Vehicle result = vehicleService.getVehicle(vehicleId);

        assertNotNull(result);
        assertEquals(vehicle, result);
        verify(vehicleRepo, times(1)).findById(vehicleId);
    }

    @Test
    void testGetVehiclesByUserId() {
        int userId = 1;
        Vehicle vehicle = new Vehicle();
        when(vehicleRepo.findByUserId(userId)).thenReturn(Arrays.asList(vehicle));

        List<Vehicle> result = vehicleService.getVehiclesByUserId(userId);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(vehicleRepo, times(1)).findByUserId(userId);
    }
}
