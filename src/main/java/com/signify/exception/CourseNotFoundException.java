package com.signify.exception;

public class CourseNotFoundException extends Exception {
	private String courseCode;

	public CourseNotFoundException(String courseCode) {
		this.courseCode = courseCode;
	}

	@Override
	public String getMessage() {
		return "Invalid Course Code";
	}
}
