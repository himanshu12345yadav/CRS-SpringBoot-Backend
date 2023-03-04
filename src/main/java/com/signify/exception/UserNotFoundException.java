package com.signify.exception;

public class UserNotFoundException extends Exception {
	private String message;

	public UserNotFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return "Invalid Username or password";
	}
}
