package com.aravind.car.service;

import java.util.List;

import com.aravind.car.DTO.UserDto;
import com.aravind.car.model.User;

public interface UserService {
	User login(String email, String password);

	public String addUser(User user);

	public String updateUser(UserDto userDto);

	public String deleteUser(int userId);

	public List<User> getAllUser();

	public User getUser(int userId);

	public User findUserByEmail(String email);
	
	public List<User> findUserStatus(String accountStatus);
	

}
