package com.aravind.car.repository;

import java.util.List;

import com.aravind.car.DTO.UserDto;
import com.aravind.car.model.User;

public interface UserRepo {
	public String save(User user);

	public String update(UserDto userDto);

	public String delete(int userId);

	public List<User> findAllUser();

	public User findById(int userId);

	public User findByEmailAndPassword(String email, String password);

	public User findByEmail(String email);

	public List<User> findAllByStatus(String accountStatus);


}
