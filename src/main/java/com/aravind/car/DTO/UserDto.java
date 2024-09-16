package com.aravind.car.DTO;

import com.aravind.car.model.User;

public class UserDto {

	private int userId;
	private String name;
	private String email;
	private String mobile;
	private String dob;
	private String address;
	private String city;
	private String state;
	private String gender;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public UserDto(User user) {

		super();
		this.userId = user.getUserId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.mobile = user.getMobile();
		this.dob = user.getDob();
		this.address = user.getAddress();
		this.city = user.getCity();
		this.state = user.getState();
		this.gender = user.getGender();

	}

	public UserDto() {
		super();
	 
	}


}
