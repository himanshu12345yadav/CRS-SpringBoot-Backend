package com.signify.bean;

import java.io.Serializable;
import java.util.Date;

public class SemesterRegistration implements Serializable {
	private int studentId, semester;

	public SemesterRegistration(int studentId, int semester, Date dateOfRegistration) {
		super();
		this.studentId = studentId;
		this.semester = semester;
		this.dateOfRegistration = dateOfRegistration;
	}

	private Date dateOfRegistration;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}

	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}

}
