package com.signify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.signify.bean.Student;
import com.signify.exception.ProfessorNotAssignedException;
import com.signify.exception.StudentNotRegisteredException;
import com.signify.jdbc.ProfessorDAO;

@Service
public class ProfessorService extends UserService implements ProfessorInterface {

	@Autowired
	private ProfessorDAO professordao;

	@Override
	public List<Student> getEnrolledStudents(String professorId) throws ProfessorNotAssignedException {
		List<Student> students = null;
		students = professordao.getEnrolledStudents(professorId);
		return students;
	}

	@Override
	public void addGrade(String professorId, String studentId, String grade)
			throws StudentNotRegisteredException, ProfessorNotAssignedException {
		professordao.addGrades(professorId, studentId, grade);
		return;
	}

}
