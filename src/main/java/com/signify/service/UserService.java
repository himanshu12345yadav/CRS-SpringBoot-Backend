package com.signify.service;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import com.signify.bean.User;
import com.signify.exception.UserAlreadyExistsException;
import com.signify.exception.UserNotFoundException;
import com.signify.jdbc.UserDAO;

@Service
@Primary
public class UserService implements UserInterface {

	@Autowired
	private UserDAO userdao;

	@Override
	public User login(String username, String password) throws UserNotFoundException {
		return userdao.login(username, password);
	}

	@Override
	public void logout() {
		// TODO : Clear Session from the client
	}

	@Override
	public void register(User usr) throws UserAlreadyExistsException {
		userdao.registerUser(usr);
	}

	@Override
	public void updatePassword(String username, String password, String newPassword) throws UserNotFoundException {
		userdao.updatePassword(username, password, newPassword);
	}
}
