package com.signify.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Repository;

import com.signify.bean.Admin;
import com.signify.bean.Professor;
import com.signify.bean.Student;
import com.signify.bean.User;
import com.signify.constants.DBConstants;
import com.signify.exception.UserAlreadyExistsException;
import com.signify.exception.UserNotFoundException;
import com.signify.utils.DBUtils;

@Repository
public class UserDAO implements IUserDAO {

	@Override
	public User login(String username, String password) throws UserNotFoundException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement st, st_student;
		User usr = null;
		try {
			st = conn.prepareStatement(DBConstants.GET_USER);
			st.setString(1, username);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();

			if (rs.next() == true) {
				do {
					int roleid = rs.getInt("roleid");
					if (roleid == DBConstants.ADMIN_ROLE_ID) {
						usr = new Admin(rs.getString("userid"), rs.getString("username"));
					} else if (roleid == DBConstants.PROFESSOR_ROLE_ID) {
						usr = new Professor(rs.getString("userid"), rs.getString("username"));
					} else if (roleid == DBConstants.STUDENT_ROLE_ID) {
						Student student = new Student(rs.getString("userid"), rs.getString("username"));

						st_student = conn.prepareStatement(DBConstants.GET_STUDENT);
						st_student.setString(1, username);
						st_student.setString(2, password);
						ResultSet rs_student = st_student.executeQuery();

						while (rs_student.next()) {
							student.setStudentId(rs_student.getString("studentid"));
						}
						usr = student;
					}
				} while (rs.next());
			} else {
				throw new UserNotFoundException(username);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usr;
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
	}

	@Override
	public void updatePassword(String username, String oldPassword, String newPassword) throws UserNotFoundException {
		Connection conn;
		PreparedStatement st;
		try {
			conn = DBUtils.getConnection();
			st = conn.prepareStatement(DBConstants.SET_NEW_PASSWORD);
			st.setString(1, newPassword);
			st.setString(2, username);
			st.setString(3, oldPassword);
			int row_count = st.executeUpdate();

			if (row_count == 0) {
				throw new UserNotFoundException(username);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void registerUser(User usr) throws UserAlreadyExistsException {
		Connection conn;
		PreparedStatement st;
		try {
			conn = DBUtils.getConnection();
			st = conn.prepareStatement(DBConstants.REGISTER_USER);

			st.setString(1, usr.getUserId());
			st.setString(2, usr.getUsername());
			st.setString(3, usr.getPassword());
			st.setString(4, usr.getAddress());
			st.setDate(5, Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
			st.setInt(6, usr.getRoleid());
			st.executeUpdate();

			PreparedStatement user_query;

			if (usr instanceof Student) {
				user_query = conn.prepareStatement(DBConstants.REGISTER_STUDENT);
				user_query.setString(1, usr.getUserId());
				user_query.setString(2, ((Student) usr).getStudentId());
				user_query.setString(3, usr.getUsername());
				user_query.setString(4, ((Student) usr).getBranch());
				user_query.setInt(5, ((Student) usr).getAge());
				user_query.setString(6, ((Student) usr).getBloodGroup());
				user_query.setString(7, ((Student) usr).getfName());
				user_query.setString(8, ((Student) usr).getPhNum());
				user_query.setInt(9, 0);
				user_query.setInt(10, 0);
				user_query.setInt(11, 0);
				user_query.executeUpdate();
			} else if (usr instanceof Professor) {
				user_query = conn.prepareStatement(DBConstants.REGISTER_PROFESSOR);
				user_query.setString(1, usr.getUserId());
				user_query.setString(2, usr.getUsername());
				user_query.setString(3, ((Professor) usr).getDepartment());
				user_query.setString(4, ((Professor) usr).getDepartment());
				user_query.setString(5, null);
				user_query.executeUpdate();
			} else if (usr instanceof Admin) {
				user_query = conn.prepareStatement(DBConstants.REGISTER_ADMIN);
				user_query.setString(1, usr.getUserId());
				user_query.setString(2, usr.getUsername());
				user_query.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
