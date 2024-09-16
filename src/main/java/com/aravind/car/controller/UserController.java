package com.aravind.car.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aravind.car.DTO.UserDto;
import com.aravind.car.model.User;
import com.aravind.car.repository.UserRepo;
import com.aravind.car.service.EmailService;
import com.aravind.car.serviceimpl.UserServiceImpl;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	UserServiceImpl service;

	@Autowired
	UserRepo userRepo;

	@Autowired
	EmailService emailService;

	static final String SUCCESS = "Success";
	static final String FAILURE = "Failure";

//	@PostMapping("/login")
//	public User login(@RequestBody User user) {
//		return service.login(user.getEmail(), user.getPassword());
//	}

//	@PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody User user) {
//        String email = user.getEmail();
//        String password = user.getPassword();
//
//        if (email == null || email.isEmpty()) {
//            return ResponseEntity.badRequest().body("Email is required.");
//        }
//        if (password == null || password.isEmpty()) {
//            return ResponseEntity.badRequest().body("Password is required.");
//        }
//
//        try {
//            User authenticatedUser = service.login(email, password);
//
//            if (authenticatedUser == null) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
//            }
//
//            // Since the repository method already filters out 'Pending' status,
//            // this check is redundant but could be added for completeness
//            if ("Pending".equals(authenticatedUser.getAccountStatus())) {
//                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Your registration is under progress. Once completed, you will be able to login.");
//            }
//
//            return ResponseEntity.ok(authenticatedUser);
//
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred. Please try again later.");
//        }
//    }

//	@PostMapping("/login")
//	public ResponseEntity<?> loginUser(@RequestBody User user) {
//		String email = user.getEmail();
//		String password = user.getPassword();
//
//		// Validate email and password
//		if (email == null || email.trim().isEmpty()) {
//			return ResponseEntity.badRequest().body("Email is required.");
//		}
//		if (password == null || password.trim().isEmpty()) {
//			return ResponseEntity.badRequest().body("Password is required.");
//		}
//
//		try {
//			// Attempt to find the user
//			User authenticatedUser = service.login(email, password);
//
//			if (authenticatedUser == null) {
//				// If user is not found, check if the credentials are correct
//				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
//			}
//
//			// Check account status
//			if ("Pending".equals(authenticatedUser.getAccountStatus())) {
//				return ResponseEntity.status(HttpStatus.FORBIDDEN)
//						.body("Your registration is under progress. Once completed, you will be able to login.");
//			}
//
//			// If all checks pass, return the user
//			return ResponseEntity.ok(authenticatedUser);
//
//		} catch (Exception e) {
//			// Log the exception for debugging purposes
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body("An error occurred. Please try again later.");
//		}
//	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user) {
		String email = user.getEmail();
		String password = user.getPassword();

		if (email == null || email.trim().isEmpty()) {
			return ResponseEntity.badRequest().body("Email is required.");
		}
		if (password == null || password.trim().isEmpty()) {
			return ResponseEntity.badRequest().body("Password is required.");
		}

		try {
			User authenticatedUser = service.login(email, password);

			if (authenticatedUser == null) {
				// If authenticatedUser is null, it means either the credentials were wrong or
				// the account status is pending.
				// Since we need to differentiate between invalid credentials and pending
				// status, we need an additional check.
				User userWithPendingStatus = userRepo.findByEmail(email);
				if (userWithPendingStatus != null && "Pending".equals(userWithPendingStatus.getAccountStatus())) {
					return ResponseEntity.status(HttpStatus.FORBIDDEN)
							.body("Your registration is under progress. Once completed, you will be able to login.");
				}
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
			}

			// Successful login with approved status
			return ResponseEntity.ok(authenticatedUser);

		} catch (Exception e) {
			e.printStackTrace(); // For debugging purposes
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred. Please try again later.");
		}
	}

	@PostMapping
	String insertUser(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("mobile") String mobile, @RequestParam("dob") String dob,
			@RequestParam("address") String address, @RequestParam("city") String city,
			@RequestParam("state") String state, @RequestParam("password") String password,
			@RequestParam("gender") String gender, @RequestParam("profileImg") MultipartFile profileImg,
			@RequestParam("accountStatus") String accountStatus, @RequestParam("idProof") MultipartFile idProof) {
		String msg = "";
		try {
			byte[] profileImgBytes = profileImg.getBytes();
			byte[] idProofBytes = idProof.getBytes();

			User user = new User(0, name, email, mobile, dob, address, city, state, password, gender, profileImgBytes,
					accountStatus, idProofBytes);

			service.addUser(user);
			msg = SUCCESS;
		} catch (IOException e) {
			e.printStackTrace();
			msg = FAILURE;
		} catch (Exception e) {
			e.printStackTrace();
			msg = FAILURE;
		}
		return msg;
	}

	@GetMapping("{userId}")
	public User getUserById(@PathVariable("userId") int id) {

		return service.getUser(id);
	}

	@GetMapping("/all")
	public List<User> getUsers() {

		return service.getAllUser();
	}

	@PutMapping("{userId}")
	public ResponseEntity<String> updateUser(@PathVariable("userId") int userId, @RequestParam("name") String name,
			@RequestParam("email") String email, @RequestParam("mobile") String mobile, @RequestParam("dob") String dob,
			@RequestParam("address") String address, @RequestParam("city") String city,
			@RequestParam("state") String state, @RequestParam("gender") String gender) {
		try {

			UserDto userDto = new UserDto();
			userDto.setUserId(userId);
			userDto.setName(name);
			userDto.setEmail(email);
			userDto.setMobile(mobile);
			userDto.setDob(dob);
			userDto.setAddress(address);
			userDto.setCity(city);
			userDto.setState(state);
			userDto.setGender(gender);

			// Update user details using the service
			service.updateUser(userDto);

			return ResponseEntity.ok("Success");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failure");
		}
	}

	@DeleteMapping("{userId}")
	public String deleteUserById(@PathVariable("userId") int id) {
		String msg = "";

		try {
			service.deleteUser(id);
			msg = SUCCESS;
		} catch (Exception e) {
			msg = FAILURE;
		}

		return msg;

	}

	@GetMapping("/{userId}/profile-image")
	public ResponseEntity<byte[]> getProfileImage(@PathVariable("userId") int userId) {
		User user = service.getUser(userId);
		if (user != null && user.getProfileImg() != null) {
			byte[] profileImgBytes = user.getProfileImg();
			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG) // Adjust if needed (e.g., PNG)
					.body(profileImgBytes);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/sendEmail/{userId}/{accountStatus}")
	public ResponseEntity<String> sendEmail(@PathVariable int userId, @PathVariable String accountStatus) {
		try {
			emailService.sendEmail(userId, accountStatus);
			return ResponseEntity.ok("Email sent successfully");
		} catch (MessagingException | IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to send email: " + e.getMessage());
		}
	}

	@PostMapping("/sendEmail")
	public ResponseEntity<String> sendEmail(@RequestParam String from, @RequestParam String to,
			@RequestParam String subject, @RequestParam String body) {
		try {
			emailService.sendEmail(to, subject, body);
			return ResponseEntity.ok("Email sent successfully");
		} catch (MessagingException | IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to send email: " + e.getMessage());
		}
	}
 
}
