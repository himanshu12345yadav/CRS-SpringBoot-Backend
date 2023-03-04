package com.signify.jdbc;

import java.util.List;

import com.signify.bean.Admin;
import com.signify.bean.Course;
import com.signify.bean.Professor;
import com.signify.bean.Student;
import com.signify.exception.CourseNotAssignedException;
import com.signify.exception.CourseNotFoundException;
import com.signify.exception.CourseNotRegisteredException;
import com.signify.exception.ProfessorNotFoundException;
import com.signify.exception.StudentNotRegisteredException;
import com.signify.exception.UserAlreadyExistsException;
import com.signify.exception.UserNotFoundException;

public interface IAdminDAO {
	public void approveAllStudents();

	public void approveStudentById(String studentId) throws UserNotFoundException;

	public List<Student> getUnapprovedStudents();

	public void addAdmin(Admin admin) throws UserAlreadyExistsException;

	public List<Admin> getAdmins();

	public List<Professor> getProfessors();

	public void addProfessor(Professor professor) throws ProfessorNotFoundException, UserAlreadyExistsException;

	public void assignProfessorToCourse(String professorid, String courseCode) throws CourseNotAssignedException;

	public void addCourse(Course course) throws CourseNotAssignedException;

	public void removeCourse(String courseCode) throws CourseNotFoundException;

	public Course getCourseDetails(String courseCode) throws CourseNotFoundException;

	public List<Course> getCourses();

	public double calculateCpi(String studentId) throws UserNotFoundException, CourseNotRegisteredException;

	public void generateReportCard(String studentId) throws StudentNotRegisteredException, UserNotFoundException;
}
