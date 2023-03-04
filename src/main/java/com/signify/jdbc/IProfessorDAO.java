package com.signify.jdbc;

import java.util.List;

import com.signify.bean.Student;
import com.signify.exception.ProfessorNotAssignedException;
import com.signify.exception.StudentNotRegisteredException;

public interface IProfessorDAO {
	public List<Student> getEnrolledStudents(String professorId) throws ProfessorNotAssignedException;
	public void addGrades(String professorId , String studentId , String grade) throws StudentNotRegisteredException , ProfessorNotAssignedException;
}
