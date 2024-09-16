package com.aravind.car.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aravind.car.DTO.UserDto;
import com.aravind.car.model.User;
import com.aravind.car.repository.UserRepo;
import com.aravind.car.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;

	@Override
	public String addUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public String updateUser(UserDto userDto) {
		return userRepo.update(userDto);
	}

	@Override
	public String deleteUser(int userId) {
		return userRepo.delete(userId);
	}

	@Override
	public List<User> getAllUser() {
		return userRepo.findAllUser();
	}

	@Override
	public User getUser(int userId) {
		return userRepo.findById(userId);
	}

	public User login(String email, String password) {
		User user = userRepo.findByEmailAndPassword(email, password);
		if (user != null && ("Pending".equals(user.getAccountStatus()) || "Rejected".equals(user.getAccountStatus()))) {
			return null; // Indicate that the registration is pending
		}
		return user;
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public List<User> findUserStatus(String accountStatus) {
		return userRepo.findAllByStatus(accountStatus);
	}

}
