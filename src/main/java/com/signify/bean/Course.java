package com.signify.bean;

import java.io.Serializable;

public class Course implements Serializable {
	private String courseCode, courseName, instructor;
	private double fee;
	private int semester, capacity;

	public Course(String courseCode, String courseName, String instructor, double coursefee, int semester) {
		super();
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.instructor = instructor;
		this.fee = coursefee;
		this.semester = semester;
	}

	public Course() {
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double coursefee) {
		this.fee = coursefee;
	}
}
