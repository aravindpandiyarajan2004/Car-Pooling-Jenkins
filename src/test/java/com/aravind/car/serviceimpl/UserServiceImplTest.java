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

import com.aravind.car.DTO.UserDto;
import com.aravind.car.model.User;
import com.aravind.car.repository.UserRepo;

public class UserServiceImplTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUser() {
        User user = new User();
        when(userRepo.save(user)).thenReturn("Success");

        String result = userService.addUser(user);

        assertEquals("Success", result);
        verify(userRepo, times(1)).save(user);
    }

    @Test
    void testUpdateUser() {
        UserDto userDto = new UserDto();
        when(userRepo.update(userDto)).thenReturn("Success");

        String result = userService.updateUser(userDto);

        assertEquals("Success", result);
        verify(userRepo, times(1)).update(userDto);
    }

    @Test
    void testDeleteUser() {
        int userId = 1;
        when(userRepo.delete(userId)).thenReturn("Success");

        String result = userService.deleteUser(userId);

        assertEquals("Success", result);
        verify(userRepo, times(1)).delete(userId);
    }

    @Test
    void testGetAllUser() {
        User user1 = new User();
        User user2 = new User();
        when(userRepo.findAllUser()).thenReturn(Arrays.asList(user1, user2));

        List<User> result = userService.getAllUser();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userRepo, times(1)).findAllUser();
    }

    @Test
    void testGetUser() {
        int userId = 1;
        User user = new User();
        when(userRepo.findById(userId)).thenReturn(user);

        User result = userService.getUser(userId);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepo, times(1)).findById(userId);
    }

    @Test
    void testLogin() {
        String email = "test@example.com";
        String password = "password";
        User user = new User();
        user.setAccountStatus("Active");
        when(userRepo.findByEmailAndPassword(email, password)).thenReturn(user);

        User result = userService.login(email, password);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepo, times(1)).findByEmailAndPassword(email, password);
    }

    @Test
    void testLoginPendingAccount() {
        String email = "test@example.com";
        String password = "password";
        User user = new User();
        user.setAccountStatus("Pending");
        when(userRepo.findByEmailAndPassword(email, password)).thenReturn(user);

        User result = userService.login(email, password);

        assertNull(result);
        verify(userRepo, times(1)).findByEmailAndPassword(email, password);
    }

    @Test
    void testFindUserByEmail() {
        String email = "test@example.com";
        User user = new User();
        when(userRepo.findByEmail(email)).thenReturn(user);

        User result = userService.findUserByEmail(email);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepo, times(1)).findByEmail(email);
    }

    @Test
    void testFindUserStatus() {
        String accountStatus = "Active";
        User user1 = new User();
        User user2 = new User();
        when(userRepo.findAllByStatus(accountStatus)).thenReturn(Arrays.asList(user1, user2));

        List<User> result = userService.findUserStatus(accountStatus);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userRepo, times(1)).findAllByStatus(accountStatus);
    }
}
