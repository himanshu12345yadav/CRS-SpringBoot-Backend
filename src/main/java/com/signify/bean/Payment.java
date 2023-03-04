package com.signify.bean;

import java.io.Serializable;

public class Payment implements Serializable {
	private String studentId, referenceId, mode;
	private double amount;
	private int status;

	public Payment() {
		// default constructor
	}

	public Payment(String studentId, int status, int amount, String mode, String referenceId) {
		this.studentId = studentId;
		this.status = status;
		this.mode = mode;
		this.amount = amount;
		this.referenceId = referenceId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
