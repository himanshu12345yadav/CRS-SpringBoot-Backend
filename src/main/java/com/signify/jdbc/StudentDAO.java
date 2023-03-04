package com.signify.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.signify.bean.Course;
import com.signify.bean.OfflinePayment;
import com.signify.bean.OnlinePayment;
import com.signify.bean.RegisteredCourse;
import com.signify.bean.Student;
import com.signify.constants.DBConstants;
import com.signify.exception.CourseLimitExceedException;
import com.signify.exception.CourseNotFoundException;
import com.signify.exception.SemesterNotRegisteredException;
import com.signify.exception.StudentNotApprovedException;
import com.signify.exception.StudentNotRegisteredException;
import com.signify.utils.DBUtils;

@Repository
public class StudentDAO implements IStudentDAO {

	@Override
	public void register(Student student) {

	}

	@Override
	public void semesterRegistration(String studentId, int semester) throws StudentNotRegisteredException {
		Connection conn = DBUtils.getConnection();

		try (PreparedStatement stmtRegisterSemester = conn.prepareStatement(DBConstants.SEMESTER_REGISTRATION)) {
			stmtRegisterSemester.setInt(1, semester);
			stmtRegisterSemester.setString(2, studentId);

			int rows = stmtRegisterSemester.executeUpdate();

			if (rows == 0) {
				throw new StudentNotRegisteredException(studentId);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getStudentId(String userId) {
		Connection conn = DBUtils.getConnection();

		String studentId = null;

		try (PreparedStatement stmtGetStudentID = conn.prepareStatement(DBConstants.GET_STUDENT_ID);) {
			stmtGetStudentID.setString(1, userId);
			ResultSet rs = stmtGetStudentID.executeQuery();

			while (rs.next()) {
				studentId = rs.getString("studentid");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return studentId;
	}

	@Override
	public boolean getApprovalStatus(String studentId) throws StudentNotApprovedException {
		Connection conn = DBUtils.getConnection();
		boolean isapproved = false;

		try (PreparedStatement stmtGetApprovalStatus = conn.prepareStatement(DBConstants.GET_APPROVAL_STATUS)) {
			stmtGetApprovalStatus.setString(1, studentId);

			ResultSet rs = stmtGetApprovalStatus.executeQuery();

			while (rs.next()) {
				isapproved = rs.getInt("isapproved") == 1;
			}

			if (!isapproved) {
				throw new StudentNotApprovedException(studentId);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isapproved;
	}

	@Override
	public List<Course> getAvailableCourses(String studentId) throws SemesterNotRegisteredException {
		Connection conn = DBUtils.getConnection();
		List<Course> courses = new ArrayList<Course>();

		try (PreparedStatement stmtGetStudentID = conn.prepareStatement(DBConstants.GET_SEMESTER);
				PreparedStatement stmtGetAvailableCourses = conn.prepareStatement(DBConstants.GET_AVAILABLE_COURSES);) {
			stmtGetStudentID.setString(1, studentId);
			ResultSet rs = stmtGetStudentID.executeQuery();
			rs.next();
			if (rs.wasNull()) {
				throw new SemesterNotRegisteredException(studentId);
			}
			int semester = rs.getInt("semester");
			while (rs.next()) {
				studentId = rs.getString("studentid");
			}

			stmtGetAvailableCourses.setInt(1, semester);
			ResultSet rsCourses = stmtGetAvailableCourses.executeQuery();
			while (rsCourses.next()) {
				Course course = new Course();
				course.setCourseCode(rsCourses.getString("coursecode"));
				course.setCourseName(rsCourses.getString("coursename"));
				course.setInstructor(rsCourses.getString("instructor"));
				courses.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return courses;
	}

	@Override
	public void addCourse(String studentId, String courseCode, int type)
			throws CourseNotFoundException, CourseLimitExceedException {
		Connection conn = DBUtils.getConnection();

		try (PreparedStatement stmtAddCourse = conn.prepareStatement(DBConstants.STUDENT_ADD_COURSE)) {
			stmtAddCourse.setString(1, courseCode);
			stmtAddCourse.setString(2, studentId);
			stmtAddCourse.setString(3, "NA");
			stmtAddCourse.setInt(4, type);

			int queryStatus = stmtAddCourse.executeUpdate();

			if (queryStatus == 0) {
				throw new CourseNotFoundException(courseCode);
			}

		} catch (SQLException e) {
			// ! Handle duplicate entry exception
			e.printStackTrace();
		}
	}

	@Override
	public void dropCourse(String studentId, String courseCode) throws CourseNotFoundException {
		Connection conn = DBUtils.getConnection();

		try (PreparedStatement dropStudentCourse = conn.prepareStatement(DBConstants.DROP_STUDENT_COURSE)) {
			dropStudentCourse.setString(1, courseCode);
			dropStudentCourse.setString(2, studentId);

			int rows = dropStudentCourse.executeUpdate();

			if (rows == 0) {
				throw new CourseNotFoundException(courseCode);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<RegisteredCourse> getGrades(String studentId) {
		Connection conn = DBUtils.getConnection();
		List<RegisteredCourse> courses = new ArrayList<RegisteredCourse>();

		try (PreparedStatement getStudentGrades = conn.prepareStatement(DBConstants.GET_STUDENT_GRADES)) {
			getStudentGrades.setString(1, studentId);
			ResultSet rs = getStudentGrades.executeQuery();
			// ! Check this query

			while (rs.next()) {
				RegisteredCourse course = new RegisteredCourse();
				String coursecode = rs.getString("coursecode");
				String coursename = rs.getString("coursename");
				String grades = rs.getString("grade");
				course.setCourseName(coursename);
				course.setCourseCode(coursecode);
				course.setCourseName(coursename);
				course.setGrade(grades);
				courses.add(course);
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return courses;
	}

	@Override
	public List<RegisteredCourse> getRegisteredCourses(String studentId) {
		Connection conn = DBUtils.getConnection();
		List<RegisteredCourse> courses = new ArrayList<RegisteredCourse>();

		try (PreparedStatement stmtGetRegisteredCourse = conn.prepareStatement(DBConstants.GET_REGISTERED_COURSE)) {
			stmtGetRegisteredCourse.setString(1, studentId);
			ResultSet rs = stmtGetRegisteredCourse.executeQuery();

			while (rs.next()) {
				RegisteredCourse course = new RegisteredCourse();
				String coursecode = rs.getString("coursecode");
				String coursename = rs.getString("coursename");
				int type = rs.getInt("type");

				course.setCourseCode(coursecode);
				course.setCourseName(coursename);
				course.setType(type);
				courses.add(course);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return courses;
	}

	@Override
	public List<Course> getFees(String studentId) {
		Connection conn = DBUtils.getConnection();

		List<Course> courses = new ArrayList<Course>();

		try (PreparedStatement stmtGetFees = conn.prepareStatement(DBConstants.GET_FEES)) {
			stmtGetFees.setString(1, studentId);

			ResultSet rs = stmtGetFees.executeQuery();

			while (rs.next()) {
				Course course = new Course();
				String coursecode = rs.getString("coursecode");
				String coursename = rs.getString("coursename");
				int fee = rs.getInt("coursefee");
				course.setCourseCode(coursecode);
				course.setCourseName(coursename);
				course.setFee(fee);
				courses.add(course);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return courses;
	}

	@Override
	public void payFeesOffline(OfflinePayment payment) {
		Connection conn = DBUtils.getConnection();

		try (PreparedStatement stmtPayFeeByCheque = conn.prepareStatement(DBConstants.PAYMENT);
				PreparedStatement stmtPayFeeOffline = conn.prepareStatement(DBConstants.PAYMENT_OFFLINE)) {

			stmtPayFeeByCheque.setString(1, payment.getReferenceId());
			stmtPayFeeByCheque.setString(2, payment.getStudentId());
			stmtPayFeeByCheque.setString(3, payment.getMode());
			stmtPayFeeByCheque.setString(4, "PAID");
			stmtPayFeeByCheque.setDouble(5, payment.getAmount());

			stmtPayFeeByCheque.execute();

			stmtPayFeeOffline.setString(1, payment.getReferenceId());
			stmtPayFeeOffline.setInt(2, payment.isCash());
			stmtPayFeeOffline.setInt(3, payment.getChequeNumber());
			stmtPayFeeOffline.setString(4, payment.getBankName());

			stmtPayFeeOffline.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void payFeesOnline(OnlinePayment payment) {
		Connection conn = DBUtils.getConnection();

		try (PreparedStatement stmtPayFeeByCard = conn.prepareStatement(DBConstants.PAYMENT);
				PreparedStatement stmtPayFeeOnline = conn.prepareStatement(DBConstants.PAYMENT_ONLINE)) {

			stmtPayFeeByCard.setString(1, payment.getReferenceId());
			stmtPayFeeByCard.setString(2, payment.getStudentId());
			stmtPayFeeByCard.setString(3, payment.getMode());
			stmtPayFeeByCard.setString(4, "PAID");
			stmtPayFeeByCard.setDouble(5, payment.getAmount());

			stmtPayFeeByCard.execute();

			stmtPayFeeOnline.setString(1, payment.getReferenceId());
			stmtPayFeeOnline.setInt(2, payment.isCard());
			stmtPayFeeOnline.setString(3, payment.getCardNumber());
			stmtPayFeeOnline.setString(4, payment.getCardType());
			stmtPayFeeOnline.setString(5, payment.getModeOfTransfer());
			stmtPayFeeOnline.setString(6, payment.getAccountNumber());
			stmtPayFeeOnline.setString(7, payment.getIfsccode());

			stmtPayFeeOnline.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
