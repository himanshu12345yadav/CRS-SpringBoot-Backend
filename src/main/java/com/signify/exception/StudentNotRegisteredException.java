package com.signify.exception;

public class StudentNotRegisteredException extends Exception {
	private String studentId;

	public StudentNotRegisteredException(String studentId) {
		this.studentId = studentId;
	}

	@Override
	public String getMessage() {
		return "Student Not Registered for this Course";
	}
}
