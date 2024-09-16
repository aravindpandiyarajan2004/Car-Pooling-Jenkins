package com.aravind.car.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment_tbl")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int payId;

	@Column
	private int amount;

	@Column
	private String payMethod;

	@Column
	private String status;

	@Column
	private Date payDate;

	@ManyToOne
	@JoinColumn(name = "user_Id")
	private User user;

	public Payment() {
		super();
	}

	public Payment(int payId, int amount, String payMethod, String status, Date payDate, User user) {
		super();
		this.payId = payId;
		this.amount = amount;
		this.payMethod = payMethod;
		this.status = status;
		this.payDate = payDate;
		this.user = user;
	}

	public int getPayId() {
		return payId;
	}

	public void setPayId(int payId) {
		this.payId = payId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Payment [payId=" + payId + ", amount=" + amount + ", payMethod=" + payMethod + ", status=" + status
				+ ", payDate=" + payDate + ", user=" + user + "]";
	}

}
