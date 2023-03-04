package com.signify.bean;

import java.io.Serializable;

public class Professor extends User implements Serializable {

	private String department, designation, course;

	public Professor(String userId, String username) {
		super(userId, username);
	}

	public Professor() {
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

}
