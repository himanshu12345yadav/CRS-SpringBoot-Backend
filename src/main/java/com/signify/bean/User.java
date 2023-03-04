package com.signify.bean;

import java.io.Serializable;

public class User implements Serializable {
	private String userId, username, password, address, doj;
	private int roleid;
	// ! check if username and name handled seperately

	public User() {
	}

	public User(String userId, String username) {
		super();
		this.userId = userId;
		this.username = username;
	}

	public User(String userid, String username, String password, String address, int roleid) {
		super();
		this.userId = userid;
		this.password = password;
		this.username = username;
		this.address = address;
		this.setRoleid(roleid);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return roleid;
	}

	public void setRoleId(int roleid) {
		this.roleid = roleid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public String getDOJ() {
		return doj;
	}

	public void setDOJ(String doj) {
		this.doj = doj;
	}

}
