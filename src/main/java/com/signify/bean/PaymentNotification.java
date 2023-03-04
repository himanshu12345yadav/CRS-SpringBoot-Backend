package com.signify.bean;

import java.io.Serializable;

public class PaymentNotification implements Serializable {
	private int studentId, referenceId, notificationId;
	private String notificationMessage;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getReferenceId() {
		return referenceId;
	}

	public PaymentNotification(int studentId, int referenceId, int notificationId, String notificationMessage) {
		super();
		this.studentId = studentId;
		this.referenceId = referenceId;
		this.notificationId = notificationId;
		this.notificationMessage = notificationMessage;
	}

	public void setReferenceId(int referenceId) {
		this.referenceId = referenceId;
	}

	public int getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}

	public String getNotificationMessage() {
		return notificationMessage;
	}

	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}
}
