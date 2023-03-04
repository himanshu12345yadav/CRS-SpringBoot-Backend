package com.signify.exception;

public class UserAlreadyExistsException extends Exception {
	private String username;

	public UserAlreadyExistsException(String username) {
		this.username = username;
	}

	@Override
	public String getMessage() {
		return "User Already Exists";
	}
}
