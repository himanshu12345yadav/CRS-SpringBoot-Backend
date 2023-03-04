package com.signify.bean;

import java.io.Serializable;

public class Scholarship implements Serializable {
	private String scholarshipName;

	public String getScholarshipName() {
		return scholarshipName;
	}

	public void setScholarshipName(String scholarshipName) {
		this.scholarshipName = scholarshipName;
	}

	public Scholarship(String scholarshipName) {
		super();
		this.scholarshipName = scholarshipName;
	}

}
