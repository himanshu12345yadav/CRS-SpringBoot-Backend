package com.signify.service;

import java.util.List;

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

public interface StudentInterface {
	public void register(Student student) throws UserAlreadyExistsException;

	public String getStudentId(String userId);

	public boolean getApprovedStatus(String studentId) throws StudentNotApprovedException;

	public List<Course> getAvailableCourses(String studentId) throws SemesterNotRegisteredException;

	public void semesterRegister(String studentId, int semester) throws StudentNotRegisteredException;

	public void addCourse(String studentId, String courseCode, int type)
			throws CourseNotFoundException, CourseLimitExceedException;

	public void dropCourse(String studentId, String courseCode) throws CourseNotFoundException;

	public List<RegisteredCourse> getRegisteredCourses(String studentId);

	public List<RegisteredCourse> getGrades(String studentId);

	public List<Course> getFees(String studentId);

	public void payFeesOffline(OfflinePayment payment);

	public void payFeesOnline(OnlinePayment payment);

}
