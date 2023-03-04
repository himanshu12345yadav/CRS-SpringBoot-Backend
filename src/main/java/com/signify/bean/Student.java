package com.signify.bean;

import java.io.Serializable;

public class Student extends User implements Serializable {
	private int age, isApproved;
	private String studentId, branch, batch, studentName, bloodgroup, fName, phNum;

	public Student() {
		super();
	}

	public Student(String userId, String username) {
		super(userId, username);
	}

	public Student(String studentId, String studentName, String branch, int age, String bg, String fName, String phNum,
			int isApproved) {
		this.studentName = studentName;
		this.branch = branch;
		this.age = age;
		this.bloodgroup = bg;
		this.fName = fName;
		this.phNum = phNum;
		this.isApproved = isApproved;
		this.studentId = studentId;
	}

	public int isApproved() {
		return isApproved;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getBloodGroup() {
		return bloodgroup;
	}

	public void setBloodGroup(String bg) {
		this.bloodgroup = bg;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getPhNum() {
		return phNum;
	}

	public void setPhNum(String phNum) {
		this.phNum = phNum;
	}

	public int getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}

	public void setApproved(int isApproved) {
		this.isApproved = isApproved;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
