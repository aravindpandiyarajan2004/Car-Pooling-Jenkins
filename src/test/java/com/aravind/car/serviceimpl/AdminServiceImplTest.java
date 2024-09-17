package com.aravind.car.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.aravind.car.model.Admin;
import com.aravind.car.repository.AdminRepo;

public class AdminServiceImplTest {

    @InjectMocks
    private AdminServiceImpl adminService;

    @Mock
    private AdminRepo adminRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginSuccess() {
        String email = "admin@gmail.com";
        String password = "Admin1@";
        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setPassword(password);

        when(adminRepo.findByEmailAndPassword(email, password)).thenReturn(admin);

        // Act
        Admin result = adminService.login(email, password);

        // Assert
        assertNotNull(result);
        assertEquals(email, result.getEmail());
        assertEquals(password, result.getPassword());
        verify(adminRepo).findByEmailAndPassword(email, password);
    }

    @Test
    void testLoginFailure() {
        // Arrange
        String email = "admin@gmail.com";
        String password = "123Arav@";

        when(adminRepo.findByEmailAndPassword(email, password)).thenReturn(null);

        // Act
        Admin result = adminService.login(email, password);

        // Assert
        assertNull(result);
        verify(adminRepo).findByEmailAndPassword(email, password);
    }
}
