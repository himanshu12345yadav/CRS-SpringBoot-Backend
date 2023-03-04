package com.signify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.signify.bean.Admin;
import com.signify.bean.Course;
import com.signify.bean.Professor;
import com.signify.bean.Student;
import com.signify.exception.CourseNotAssignedException;
import com.signify.exception.CourseNotFoundException;
import com.signify.exception.CourseNotRegisteredException;
import com.signify.exception.UserAlreadyExistsException;
import com.signify.exception.UserNotFoundException;
import com.signify.jdbc.AdminDAO;

@Service
public class AdminService extends UserService implements AdminInterface {
	
	@Autowired
	private AdminDAO admindao;
	
	@Override
	public void generateReportCard(String studentId) throws UserNotFoundException {
		admindao.generateReportCard(studentId);
	}

	@Override
	public void addCourse(Course course) throws CourseNotAssignedException {
		admindao.addCourse(course);
	}

	@Override
	public void removeCourse(String courseCode) throws CourseNotFoundException {
		admindao.removeCourse(courseCode);
	}

	@Override
	public List<Course> getCourses(){
		return admindao.getCourses();
	}

	@Override
	public void assignProfessorToCourse(String professorId, String courseCode) throws CourseNotAssignedException {
		admindao.assignProfessorToCourse(professorId, courseCode);
	}

	@Override
	public Course getCourseDetails(String courseCode) throws CourseNotFoundException {
		return admindao.getCourseDetails(courseCode);
	}

	@Override
	public double calculateCpi(String studentId) throws UserNotFoundException, CourseNotRegisteredException {
		
		return admindao.calculateCpi(studentId);
	}

	@Override
	public List<Student> getUnapprovedStudents() {
		return admindao.getUnapprovedStudents();
	}

	@Override
	public void addProfessor(Professor professor) throws UserAlreadyExistsException {
		UserService userService = new UserService();
		userService.register(professor);
	}

	@Override
	public List<Professor> getProfessors() {
		AdminDAO ad = new AdminDAO();
		return ad.getProfessors();
	}

	@Override
	public void addAdmin(Admin admin) throws UserAlreadyExistsException {
		UserService userService = new UserService();
		userService.register(admin);
	}

	@Override
	public List<Admin> getAdmins() {
		AdminDAO ad = new AdminDAO();
		return ad.getAdmins();
	}

	@Override
	public void approveAllStudents() {
		AdminDAO ad = new AdminDAO();
		ad.approveAllStudents();
	}

	@Override
	public void approveStudentById(String studentId) throws UserNotFoundException {
		AdminDAO ad = new AdminDAO();
		ad.approveStudentById(studentId);
	}
}
