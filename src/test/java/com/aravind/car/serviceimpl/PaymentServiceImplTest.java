package com.aravind.car.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.aravind.car.model.Payment;
import com.aravind.car.repository.PaymentRepo;

public class PaymentServiceImplTest {

    @Mock
    private PaymentRepo paymentRepo;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPayment() {
        Payment payment = new Payment();
        when(paymentRepo.save(payment)).thenReturn("Success");

        String result = paymentService.addPayment(payment);

        assertEquals("Success", result);
        verify(paymentRepo, times(1)).save(payment);
    }

    @Test
    void testUpdatePayment() {
        Payment payment = new Payment();
        when(paymentRepo.update(payment)).thenReturn("Success");

        String result = paymentService.updatePayment(payment);

        assertEquals("Success", result);
        verify(paymentRepo, times(1)).update(payment);
    }

    @Test
    void testDeletePayment() {
        int payId = 1;
        when(paymentRepo.delete(payId)).thenReturn("Success");

        String result = paymentService.deletePayment(payId);

        assertEquals("Success", result);
        verify(paymentRepo, times(1)).delete(payId);
    }

    @Test
    void testGetAllPayment() {
        Payment payment1 = new Payment();
        Payment payment2 = new Payment();
        when(paymentRepo.findAllPayments()).thenReturn(Arrays.asList(payment1, payment2));

        var result = paymentService.getAllPayment();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(paymentRepo, times(1)).findAllPayments();
    }

    @Test
    void testGetPayment() {
        int payId = 1;
        Payment payment = new Payment();
        when(paymentRepo.findById(payId)).thenReturn(payment);

        Payment result = paymentService.getPayment(payId);

        assertNotNull(result);
        assertEquals(payment, result);
        verify(paymentRepo, times(1)).findById(payId);
    }

    @Test
    void testGetPaymentByUserId() {
        int userId = 1;
        Payment payment = new Payment();
        when(paymentRepo.findByUserId(userId)).thenReturn(Optional.of(payment));

        Optional<Payment> result = paymentService.getPaymentByUserId(userId);

        assertTrue(result.isPresent());
        assertEquals(payment, result.get());
        verify(paymentRepo, times(1)).findByUserId(userId);
    }

    @Test
    void testUpdatePaymentDetails() {
        int payId = 1;
        Payment updateRequest = new Payment();
        when(paymentRepo.updatePaymentDetails(payId, updateRequest)).thenReturn("Success");

        String result = paymentService.updatePaymentDetails(payId, updateRequest);

        assertEquals("Success", result);
        verify(paymentRepo, times(1)).updatePaymentDetails(payId, updateRequest);
    }
}
