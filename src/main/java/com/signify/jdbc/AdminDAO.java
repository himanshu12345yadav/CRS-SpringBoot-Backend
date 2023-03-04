/**
 * 
 */
package com.signify.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.signify.bean.Admin;
import com.signify.bean.Course;
import com.signify.bean.Professor;
import com.signify.bean.Student;
import com.signify.constants.DBConstants;
import com.signify.exception.CourseNotAssignedException;
import com.signify.exception.CourseNotFoundException;
import com.signify.exception.CourseNotRegisteredException;
import com.signify.exception.ProfessorNotFoundException;
import com.signify.exception.UserAlreadyExistsException;
import com.signify.exception.UserNotFoundException;
import com.signify.utils.DBUtils;

@Repository
public class AdminDAO implements IAdminDAO {
	@Override
	public void addCourse(Course course) throws CourseNotAssignedException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement st = null;
		PreparedStatement st_prof = null;

		try {
			st = conn.prepareStatement(DBConstants.INSERT_COURSE_IN_CATALOG);
			st.setString(1, course.getCourseCode());
			st.setString(2, course.getCourseName());
			st.setString(3, course.getInstructor());
			st.setDouble(4, course.getFee());
			st.setDouble(5, course.getSemester());
			st.executeUpdate();

			st_prof = conn.prepareStatement(DBConstants.UPDATE_PROFESSOR_COURSE);
			st_prof.setString(1, course.getCourseCode());
			st_prof.setString(2, course.getInstructor());
			st_prof.executeUpdate();

		} catch (SQLException e) {
			// ! check a better way - sql constraint fail
			throw new CourseNotAssignedException();
		}
	}

	@Override
	public void removeCourse(String courseCode) throws CourseNotFoundException {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DBUtils.getConnection();
			st = conn.prepareStatement(DBConstants.REMOVE_COURSE_FROM_CATALOG);
			st.setString(1, courseCode);
			int row = st.executeUpdate();

			if (row == 0) {
				throw new CourseNotFoundException(courseCode);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	@Override
	public void assignProfessorToCourse(String professorid, String courseCode) throws CourseNotAssignedException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement st = null;
		PreparedStatement st_course = null;
		try {
			st = conn.prepareStatement(DBConstants.UPDATE_COURSE_OF_PROFESSOR);
			st.setString(1, courseCode);
			st.setString(2, professorid);
			st.executeUpdate();

			st_course = conn.prepareStatement(DBConstants.UPDATE_PROFESSOR_IN_CATALOG);
			st_course.setString(1, professorid);
			st_course.setString(2, courseCode);
			st_course.close();

			st.close();
		} catch (SQLException se) {
			// ! NEEDS TO FIND A BETTER WAY
			throw new CourseNotAssignedException();
		}
	}

	@Override
	public Course getCourseDetails(String courseCode) throws CourseNotFoundException {
		Course course = new Course();
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DBUtils.getConnection();

			st = conn.prepareStatement(DBConstants.GET_COURSE_FROM_CATALOG);
			st.setString(1, courseCode);
			ResultSet rs = st.executeQuery();

			if (rs.next() == true) {
				do {
					course.setCourseCode(rs.getString("coursecode"));
					course.setCourseName(rs.getString("coursename"));
					course.setInstructor(rs.getString("instructor"));
					course.setFee(rs.getDouble("coursefee"));

				} while (rs.next());
			} else {
				throw new CourseNotFoundException(courseCode);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return course;
	}

	@Override
	public List<Course> getCourses() {
		List<Course> cs = new ArrayList<Course>();
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DBUtils.getConnection();

			st = conn.prepareStatement(DBConstants.LIST_COURSES);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Course c = new Course();
				c.setCourseCode(rs.getString("coursecode"));
				c.setCourseName(rs.getString("coursename"));
				c.setInstructor(rs.getString("instructor"));
				c.setFee(rs.getDouble("coursefee"));
				cs.add(c);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return cs;
	}

	@Override
	public List<Student> getUnapprovedStudents() {
		List<Student> students = new ArrayList<Student>();

		Connection conn;
		PreparedStatement st;

		try {
			conn = DBUtils.getConnection();
			st = conn.prepareStatement(DBConstants.GET_UNAPPROVED_STUDENTS);
			st.setInt(1, 0);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Student student = new Student();
				student.setStudentId(rs.getString("studentid"));
				student.setStudentName(rs.getString("studentname"));
				student.setBranch(rs.getString("branch"));
				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students;
	}

	@Override
	public void approveAllStudents() {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DBUtils.getConnection();
			st = conn.prepareStatement(DBConstants.APPROVE_ALL_STUDENTS);
			st.setInt(1, 1);
			st.executeUpdate();
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	@Override
	public void approveStudentById(String studentId) throws UserNotFoundException {
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DBUtils.getConnection();
			st = conn.prepareStatement(DBConstants.APPROVE_BY_ID);
			st.setInt(1, 1);
			st.setString(2, studentId);
			int row = st.executeUpdate();

			if (row == 0) {
				throw new UserNotFoundException(studentId);
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	@Override
	public List<Admin> getAdmins() {
		List<Admin> admins = new ArrayList<Admin>();
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DBUtils.getConnection();
			st = conn.prepareStatement(DBConstants.VIEW_ADMINS);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Admin admin = new Admin();
				admin.setUserId(rs.getString("adminid"));
				admin.setUsername(rs.getString("adminname"));
				admins.add(admin);
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return admins;
	}

	@Override
	public List<Professor> getProfessors() {
		List<Professor> professors = new ArrayList<Professor>();
		Connection conn = DBUtils.getConnection();

		try (PreparedStatement st = conn.prepareStatement(DBConstants.VIEW_PROFESSORS)) {
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Professor professor = new Professor();
				professor.setUserId(rs.getString("professorid"));
				professor.setUsername(rs.getString("professorname"));
				professor.setDesignation(rs.getString("designation"));
				professor.setDepartment(rs.getString("department"));
				professor.setCourse(rs.getString("course"));
				professors.add(professor);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return professors;
	}

	@Override
	public double calculateCpi(String studentId) throws UserNotFoundException, CourseNotRegisteredException {

		String grade;
		double cgpa = 0.0, cpi = 0.0;
		int n = 0;
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DBUtils.getConnection();
			st = conn.prepareStatement(DBConstants.GET_GRADES);
			st.setString(1, studentId);
			ResultSet rs = st.executeQuery();

			if (rs.next() == true) {
				do {
					grade = rs.getString("grade");
					n++;
					if (grade.equals("A+"))
						cgpa += 10.0;
					else if (grade.equals("A"))
						cgpa += 9.5;
					else if (grade.equals("A-"))
						cgpa += 8.5;
					else if (grade.equals("B"))
						cgpa += 8.0;
					else if (grade.equals("B-"))
						cgpa += 7.5;
					else if (grade.equals("C"))
						cgpa += 7.0;
					else if (grade.equals("C-"))
						cgpa += 6.5;
					else if (grade.equals("D"))
						cgpa += 6.0;
					else
						cgpa += 0;

				} while (rs.next());
			} else {
				throw new CourseNotRegisteredException();
			}

			cpi = cgpa / n;

		} catch (SQLException s) {
			s.printStackTrace();
		}
		return cpi;
	}

	@Override
	public void generateReportCard(String studentId) throws UserNotFoundException {
		System.out.println("Generating report card");
		// TODO Generate Report card
	}

	@Override
	public void addAdmin(Admin admin) throws UserAlreadyExistsException {
		Connection conn = DBUtils.getConnection();

		try (PreparedStatement stmtRegisterUser = conn.prepareStatement(DBConstants.REGISTER_USER);
				PreparedStatement stmtRegisterAdmin = conn.prepareStatement(DBConstants.REGISTER_ADMIN);) {
			stmtRegisterUser.setString(1, admin.getUserId());
			stmtRegisterUser.setString(2, admin.getUsername());
			stmtRegisterUser.setString(3, admin.getPassword());
			stmtRegisterUser.setString(4, admin.getAddress());
			stmtRegisterUser.setDate(5, Date.valueOf(admin.getDOJ()));
			stmtRegisterUser.setInt(6, admin.getRoleid());

			stmtRegisterUser.execute();

			stmtRegisterAdmin.setString(1, admin.getUserId());
			stmtRegisterAdmin.setString(2, admin.getUsername());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addProfessor(Professor professor) throws ProfessorNotFoundException, UserAlreadyExistsException {

		Connection conn = DBUtils.getConnection();

		try (PreparedStatement stmtRegisterUser = conn.prepareStatement(DBConstants.REGISTER_USER);
				PreparedStatement stmtRegisterProfessor = conn.prepareStatement(DBConstants.REGISTER_PROFESSOR)) {

			stmtRegisterUser.setString(1, professor.getUserId());
			stmtRegisterUser.setString(2, professor.getUsername());
			stmtRegisterUser.setString(3, professor.getPassword());
			stmtRegisterUser.setString(4, professor.getAddress());
			stmtRegisterUser.setDate(5, Date.valueOf(professor.getDOJ()));
			stmtRegisterUser.setInt(6, professor.getRole());

			stmtRegisterUser.execute();

			stmtRegisterProfessor.setString(1, professor.getUserId());
			stmtRegisterProfessor.setString(2, professor.getUsername());
			stmtRegisterProfessor.setString(3, professor.getDepartment());
			stmtRegisterProfessor.setString(4, professor.getDesignation());
			stmtRegisterProfessor.setString(5, null);

			stmtRegisterProfessor.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
