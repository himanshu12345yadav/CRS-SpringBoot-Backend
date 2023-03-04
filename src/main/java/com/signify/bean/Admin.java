package com.signify.bean;

import java.io.Serializable;

public class Admin extends User implements Serializable{

	public Admin(String userId, String username) {
		super(userId, username);
	}

	public Admin() {
	}

}
