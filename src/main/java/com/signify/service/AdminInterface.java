package com.signify.service;

import java.util.List;

import com.signify.bean.Admin;
import com.signify.bean.Course;
import com.signify.bean.Professor;
import com.signify.bean.Student;
import com.signify.exception.CourseNotAssignedException;
import com.signify.exception.CourseNotFoundException;
import com.signify.exception.CourseNotRegisteredException;
import com.signify.exception.ProfessorNotFoundException;
import com.signify.exception.UserAlreadyExistsException;
import com.signify.exception.UserNotFoundException;

public interface AdminInterface {

	public void addCourse(Course course) throws CourseNotAssignedException;

	public void removeCourse(String courseCode) throws CourseNotFoundException;

	public List<Course> getCourses();

	public Course getCourseDetails(String courseCode) throws CourseNotFoundException;

	public void addProfessor(Professor p) throws UserAlreadyExistsException;

	public void assignProfessorToCourse(String professorid, String courseCode) throws CourseNotFoundException, CourseNotAssignedException;

	public List<Professor> getProfessors() throws ProfessorNotFoundException;

	public void addAdmin(Admin a) throws UserAlreadyExistsException;

	public List<Admin> getAdmins();

	public List<Student> getUnapprovedStudents();

	public void approveAllStudents();

	public void approveStudentById(String studentId) throws UserNotFoundException;

	public double calculateCpi(String studentId) throws UserNotFoundException, CourseNotRegisteredException;

	public void generateReportCard(String studentId) throws UserNotFoundException;

}