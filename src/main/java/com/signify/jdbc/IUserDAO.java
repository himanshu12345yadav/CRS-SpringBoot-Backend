package com.signify.jdbc;

import com.signify.bean.User;
import com.signify.exception.UserAlreadyExistsException;
import com.signify.exception.UserNotFoundException;

public interface IUserDAO {
	public User login(String username, String password) throws UserNotFoundException;

	public void logout();

	public void updatePassword(String username, String oldpassword, String newPassword) throws UserNotFoundException;

	public void registerUser(User usr) throws UserAlreadyExistsException;
}
