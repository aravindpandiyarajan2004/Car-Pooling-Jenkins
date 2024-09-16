//package com.aravind.car.service;
//
//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import com.aravind.car.repository.BookingRepo;
//import com.aravind.car.repository.UserRepo;
//import com.aravind.micro.model.Applicant;
//import com.aravind.micro.model.Premium;
//import com.aravind.micro.repository.ApplicantRepo;
//import com.aravind.micro.repository.PremiumRepo;
//
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//
//@Service
//public class EmailService {
//
//	@Autowired
//	private JavaMailSender javaMailSender;
//
//	@Autowired
//	private UserRepo userRepo;
//
//	@Autowired
//	private BookingRepo bookingRepo;
//
//	@Value("${spring.mail.username}")
//	private String sender;
//
//	public void sendEmail(String to, String subject, String text) throws MessagingException, IOException {
//		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//
//		helper.setFrom(sender);
//		helper.setTo(to);
//		helper.setSubject(subject);
//		helper.setText(text, true); // Set true to enable HTML content
//
//		javaMailSender.send(mimeMessage);
//		System.out.println("Mail sent");
//	}
//
//	public void sendEmail(int usreId, String accountStatus) throws MessagingException, IOException {
////		Applicant applicant = applicantRepo.findById(applicantId);
////		Premium premium = premiumRepo.getPremiumByApplicantId(applicantId);
//		
//		
//
//		System.out.println("mail sending");
//
//		if (applicant != null) {
//			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//
//			helper.setFrom(sender);
//			helper.setTo(applicant.getEmail());
//			helper.setSubject("Insurance Application " + status);
//
//			if ("Approved".equalsIgnoreCase(status) && premium != null) {
//				helper.setText("Dear " + applicant.getApplicantName() + ",\r\n" + "\r\n"
//						+ "Your insurance application has been approved. Please find the premium details below:\r\n"
//						+ "\r\n" + "Total Amount: " + premium.getTotalAmount() + "\r\n" + "Monthly: "
//						+ premium.getMonthly() + "\r\n" + "Quarterly: " + premium.getQuartely() + "\r\n"
//						+ "Half-Yearly: " + premium.getHalfly() + "\r\n" + "Yearly: " + premium.getYearly() + "\r\n"
//						+ "\r\n"
//						+ "Kindly check your dashboard for more information. If you are interested, please acknowledge us.\r\n"
//						+ "\r\nThank you!");
//			} else if ("Rejected".equalsIgnoreCase(status)) {
//				helper.setText("Dear " + applicant.getApplicantName() + ",\r\n" + "\r\n"
//						+ "Your insurance application did not meet our criteria because your risk score is high.\r\n"
//						+ "\r\n" + "Thank you for your interest.\r\n" + "\r\n" + "Regards,\r\n" + "Insurance Team");
//			}
//			try {
//
//				javaMailSender.send(mimeMessage);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			System.out.println("Mail sent");
//		} else {
//			System.err.println("Applicant not found for ID: " + applicantId);
//		}
//	}
//}

package com.aravind.car.service;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.aravind.car.model.Booking;
import com.aravind.car.model.User;
import com.aravind.car.repository.UserRepo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private UserRepo userRepo;

	@Value("${spring.mail.username}")
	private String sender;

//	public void sendEmail(int userId, String accountStatus) throws MessagingException, IOException {
//		User user = userRepo.findById(userId);
//		if (user != null) {
//			String subject = "Account Status Update";
//			String text = "Dear " + user.getName() + ",\n\nYour account status has been updated to: " + accountStatus
//					+ ".\n\nThank you,\nSupport Team";
//			sendEmail(user.getEmail(), subject, text);
//		} else {
//			System.err.println("User not found for ID: " + userId);
//		}
//	}

	public void sendEmail(int userId, String accountStatus) throws MessagingException, IOException {
		User user = userRepo.findById(userId);
		if (user != null) {
			String subject = "Account Status Update";

			// HTML content for the email
			String text = "<html><body style='font-family: Arial, sans-serif; line-height: 1.6; color: #333;'>"
					+ "<h2 style='color: #4CAF50;'>Your Profile Status Has Been Updated</h2>" + "<p>Dear "
					+ user.getName() + ",</p>"
					+ "<p>We have reviewed your profile, and your account status is now: <strong>" + accountStatus
					+ "</strong>.</p>" + getStatusMessage(accountStatus)
					+ "<p>Thank you for your interest in our services.</p>"
					+ "<p>Best regards,<br>The Car Pooling Team</p>" + "</body></html>";

			sendEmail(user.getEmail(), subject, text);
		} else {
			System.err.println("User not found for ID: " + userId);
		}
	}

	private String getStatusMessage(String accountStatus) {
		if ("Approved".equalsIgnoreCase(accountStatus)) {
			return "<p style='color: #4CAF50;'>Congratulations! You are now eligible to log in to our system and start using our services. We look forward to your active participation.</p>";
		} else if ("Rejected".equalsIgnoreCase(accountStatus)) {
			return "<p style='color: #F44336;'>We regret to inform you that your profile does not meet our current criteria. We encourage you to review our requirements and consider applying again in the future.</p>";
		} else {
			return "<p>We appreciate your interest. If you have any questions or need further assistance, please do not hesitate to contact us.</p>";
		}
	}

	public void sendBookingApprovalEmail(User user, Booking booking) throws MessagingException, IOException {
		String subject = "Booking Approved!";
		String body = "<html><body style='font-family: Arial, sans-serif; line-height: 1.6; color: #333;'>"
				+ "<h2 style='color: #4CAF50;'>Booking Approved!</h2>" + "<p>Dear " + user.getName() + ",</p>"
				+ "<p>Your booking has been approved. Here are the details:</p>"
				+ "<table style='width: 100%; border-collapse: collapse; border: 1px solid #ddd;'>"
				+ "<tr><th style='background-color: #f4f4f4; padding: 8px; border: 1px solid #ddd;'>Field</th>"
				+ "<th style='background-color: #f4f4f4; padding: 8px; border: 1px solid #ddd;'>Details</th></tr>"
				+ "<tr><td style='padding: 8px; border: 1px solid #ddd;'>Requested Seats</td><td style='padding: 8px; border: 1px solid #ddd;'>"
				+ booking.getRequestedSeats() + "</td></tr>"
				+ "<tr><td style='padding: 8px; border: 1px solid #ddd;'>Booking Date</td><td style='padding: 8px; border: 1px solid #ddd;'>"
				+ booking.getBookingDate() + "</td></tr>"
				+ "<tr><td style='padding: 8px; border: 1px solid #ddd;'>Status</td><td style='padding: 8px; border: 1px solid #ddd;'>"
				+ booking.getBookingStatus() + "</td></tr>" + "</table>"
				+ "<p>Thank you for choosing our service. If you have any questions, feel free to contact us.</p>"
				+ "<p>Best regards,<br>The Car Pooling Team</p>" + "</body></html>";

		sendEmail(user.getEmail(), subject, body);
	}

	public void sendBookingRejectionEmail(User user, Booking booking) throws MessagingException, IOException {
		String subject = "Booking Rejected";
		String body = "<html><body style='font-family: Arial, sans-serif; background-color: #f8f9fa; padding: 20px;'>"
				+ "<div style='max-width: 600px; margin: auto; background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1);'>"
				+ "<h2 style='color: #dc3545; text-align: center;'>Booking Rejected</h2>" + "<p>Dear " + user.getName()
				+ ",</p>" + "<p>We regret to inform you that your booking has been rejected. Here are the details:</p>"
				+ "<table style='width: 100%; border-collapse: collapse; border: 1px solid #ddd;'>"
				+ "<tr><th style='background-color: #f4f4f4; padding: 8px; border: 1px solid #ddd;'>Field</th>"
				+ "<th style='background-color: #f4f4f4; padding: 8px; border: 1px solid #ddd;'>Details</th></tr>"
				+ "<tr><td style='padding: 8px; border: 1px solid #ddd;'>Requested Seats</td><td style='padding: 8px; border: 1px solid #ddd;'>"
				+ booking.getRequestedSeats() + "</td></tr>"
				+ "<tr><td style='padding: 8px; border: 1px solid #ddd;'>Booking Date</td><td style='padding: 8px; border: 1px solid #ddd;'>"
				+ booking.getBookingDate() + "</td></tr>"
				+ "<tr><td style='padding: 8px; border: 1px solid #ddd;'>Status</td><td style='padding: 8px; border: 1px solid #ddd;'>"
				+ booking.getBookingStatus() + "</td></tr>" + "</table>"
				+ "<p>We value your interest in our service and hope you will consider us for future bookings. If you have any questions or need further assistance, please don't hesitate to contact us.</p>"
				+ "<p>Warm regards,<br>The Car Pooling Team</p>" + "</div></body></html>";

		sendEmail(user.getEmail(), subject, body);
	}

	public void sendEmail(String to, String subject, String body) throws MessagingException, IOException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setFrom(sender);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body, true); // Set true to enable HTML content

		javaMailSender.send(mimeMessage);
		System.out.println("Mail sent to: " + to);
	}

}
