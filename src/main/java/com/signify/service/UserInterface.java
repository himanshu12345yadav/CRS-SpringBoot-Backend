package com.signify.service;

import com.signify.bean.User;
import com.signify.exception.UserAlreadyExistsException;
import com.signify.exception.UserNotFoundException;

public interface UserInterface {
	public User login(String username , String password) throws UserNotFoundException;
	public void logout();
	public void register(User usr) throws UserAlreadyExistsException;
	public void updatePassword(String username , String password , String newPassword) throws UserNotFoundException;
}
