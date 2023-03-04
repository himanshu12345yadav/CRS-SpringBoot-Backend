package com.signify.exception;

public class CourseLimitExceedException extends Exception {

	@Override
	public String getMessage() {
		return "Max Students in Course Limit Reached";
	}
}
