package com.signify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.signify.bean.Course;
import com.signify.bean.OfflinePayment;
import com.signify.bean.OnlinePayment;
import com.signify.bean.RegisteredCourse;
import com.signify.bean.Student;
import com.signify.exception.CourseLimitExceedException;
import com.signify.exception.CourseNotFoundException;
import com.signify.exception.SemesterNotRegisteredException;
import com.signify.exception.StudentNotApprovedException;
import com.signify.exception.StudentNotRegisteredException;
import com.signify.exception.UserAlreadyExistsException;
import com.signify.jdbc.StudentDAO;
import com.signify.jdbc.UserDAO;

@Service
@Primary
public class StudentService extends UserService implements StudentInterface {

	@Autowired
	private StudentDAO studentdao;
	private UserDAO userdao;

	@Override
	public void register(Student student) throws UserAlreadyExistsException {
		userdao.registerUser(student);
	}

	@Override
	public String getStudentId(String userId) {
		return studentdao.getStudentId(userId);
	}

	@Override
	public boolean getApprovedStatus(String studentId) throws StudentNotApprovedException {
		return studentdao.getApprovalStatus(studentId);
	}

	@Override
	public List<Course> getAvailableCourses(String studentId) throws SemesterNotRegisteredException {
		return studentdao.getAvailableCourses(studentId);
	}

	@Override
	public void semesterRegister(String studentId, int semester) throws StudentNotRegisteredException {
		studentdao.semesterRegistration(studentId, semester);
	}

	@Override
	public void addCourse(String studentId, String courseCode, int type)
			throws CourseNotFoundException, CourseLimitExceedException {
		studentdao.addCourse(studentId, courseCode, type);
	}

	@Override
	public void dropCourse(String studentId, String courseCode) throws CourseNotFoundException {
		studentdao.dropCourse(studentId, courseCode);
	}

	@Override
	public List<RegisteredCourse> getRegisteredCourses(String studentId) {
		return studentdao.getRegisteredCourses(studentId);
	}

	@Override
	public List<RegisteredCourse> getGrades(String studentId) {
		return studentdao.getGrades(studentId);
	}

	@Override
	public List<Course> getFees(String studentId) {
		return studentdao.getFees(studentId);
	}

	@Override
	public void payFeesOffline(OfflinePayment payment) {
		studentdao.payFeesOffline(payment);
	}

	@Override
	public void payFeesOnline(OnlinePayment payment) {
		studentdao.payFeesOnline(payment);
	}

}
