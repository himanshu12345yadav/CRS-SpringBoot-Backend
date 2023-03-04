package com.signify.service;

import java.util.List;

import com.signify.bean.Student;
import com.signify.exception.ProfessorNotAssignedException;
import com.signify.exception.StudentNotRegisteredException;

public interface ProfessorInterface {
	public List<Student> getEnrolledStudents(String professorId) throws ProfessorNotAssignedException;
	public void addGrade(String professorId , String studentId , String grade) throws StudentNotRegisteredException, ProfessorNotAssignedException;
}
