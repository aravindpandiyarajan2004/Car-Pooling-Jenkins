package com.aravind.car.model;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_tbl")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	@Column
	private String name;

	@Column
	private String email;

	@Column
	private String mobile;

	@Column
	private String dob;

	@Column
	private String address;

	@Column
	private String city;

	@Column
	private String state;

	@Column
	private String password;

	@Column
	private String gender;

	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] profileImg;

	@Column
	private String accountStatus;

	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] idProof;

	public User() {
		super();
	}

	public User(int userId, String name, String email, String mobile, String dob, String address, String city,
			String state, String password, String gender, byte[] profileImg, String accountStatus, byte[] idProof) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.dob = dob;
		this.address = address;
		this.city = city;
		this.state = state;
		this.password = password;
		this.gender = gender;
		this.profileImg = profileImg;
		this.accountStatus = accountStatus;
		this.idProof = idProof;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public byte[] getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(byte[] profileImg) {
		this.profileImg = profileImg;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public byte[] getIdProof() {
		return idProof;
	}

	public void setIdProof(byte[] idProof) {
		this.idProof = idProof;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", mobile=" + mobile + ", dob=" + dob
				+ ", address=" + address + ", city=" + city + ", state=" + state + ", password=" + password
				+ ", gender=" + gender + ", profileImg=" + Arrays.toString(profileImg) + ", accountStatus="
				+ accountStatus + ", idProof=" + Arrays.toString(idProof) + "]";
	}

}
