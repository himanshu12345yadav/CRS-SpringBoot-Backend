package com.signify.constants;

public class DBConstants {

	// Some defaults

	public static final int ADMIN_ROLE_ID = 1;
	public static final int PROFESSOR_ROLE_ID = 2;
	public static final int STUDENT_ROLE_ID = 3;
	// User Queries

	public static final String GET_USER = "select * from user where username = ? and password = ?";
	public static final String GET_STUDENT = "select * from user inner join student on user.userid = student.userid where username = ? and password = ?";
	public static final String SET_NEW_PASSWORD = "update user set password = ? where username = ? and password = ?";
	public static final String REGISTER_USER = "insert into user(userid , username , password , address , doj , roleid) values(? , ? , ? , ? , ? , ?)";
	// Student Queries
	public static String REGISTER_STUDENT = "insert into student(userid , studentid , studentname , branch , age , bloodgroup , fname , phnum , isapproved , numcourses , semester) values(?,?,?,?,?,?,?,?,?,?,?)";
	public static String GET_STUDENT_GRADES = "select rc.coursecode as coursecode, cat.coursename as coursename, rc.grade from registeredcourse rc inner join  catalog cat on cat.coursecode = rc.courseCode where rc.studentId = ?";
	public static final String REGISTER_PROFESSOR = "insert into professor values(?,?,?,?,?)";
	public static final String GET_UNAPPROVED_STUDENTS = "select * from user u inner join Student s on u.userid = s.userid where s.isapproved=?";

	// Admin Queries
	public static final String REGISTER_ADMIN = "insert into admin values(? , ?)";
	public static final String INSERT_COURSE_IN_CATALOG = "insert into catalog (coursecode, coursename, instructor, coursefee , semester) values(?,?,?,?,?)";
	public static final String UPDATE_PROFESSOR_COURSE = "update professor set course = ? where professorid = ?";
	public static final String REMOVE_COURSE_FROM_CATALOG = "delete from catalog where coursecode=?";
	public static final String UPDATE_COURSE_OF_PROFESSOR = "update professor set course = ? where professorid = ?";
	public static final String UPDATE_PROFESSOR_IN_CATALOG = "update catalog set instructor=? where coursecode=?";
	public static final String GET_COURSE_FROM_CATALOG = "select * from catalog where coursecode = ?";
	public static final String LIST_COURSES = "select * from catalog";
	public static final String APPROVE_ALL_STUDENTS = "update student set isapproved=?";
	public static final String APPROVE_BY_ID = "update student set isapproved=? where studentid=?";
	public static final String VIEW_ADMINS = "select * from admin";
	public static final String VIEW_PROFESSORS = "select * from professor";
	public static final String GET_GRADES = "select grade from registeredcourse where studentId=?";
	public static final String GET_PROFESSOR_COURSE = "select course from professor where professorid = ?";
	public static final String GET_ENROLLED_STUDENTS = "select studentid from registeredcourse inner join professor on registeredcourse.coursecode = professor.course where professorid = ?";
	public static final String UPDATE_STUDENT_GRADE = "update registeredcourse set grade = ? where studentid = ? and coursecode = ?";

	// student queries

	public static final String SEMESTER_REGISTRATION = "update student set semester = ? where studentid = ?";
	public static final String GET_STUDENT_ID = "select studentid from student where userid=?";
	public static final String GET_APPROVAL_STATUS = "select isapproved from student where studentid=?";
	public static final String GET_SEMESTER = "select semester from student where studentid = ?";
	public static final String GET_AVAILABLE_COURSES = "select distinct catalog.coursename , catalog.coursecode , catalog.instructor , catalog.capacity from catalog inner join student on catalog.semester = student.semester  where student.semester = ? and catalog.capacity <= 10";
	public static final String DROP_STUDENT_COURSE = "delete from registeredcourse where courseCode = ? and studentId = ?";
	public static final String GET_REGISTERED_COURSE = "select rc.courseCode as coursecode, cat.coursename as coursename, rc.type as type from registeredcourse rc inner join catalog cat on cat.coursecode = rc.coursecode where rc.studentId=?";
	public static final String GET_FEES = "select catalog.coursecode as coursecode , coursename , coursefee from registeredcourse inner join catalog on registeredcourse.coursecode = catalog.coursecode where studentId=?";
	public static final String PAYMENT_OFFLINE = "insert into payment_offline(referenceid , cash , chequenum, bankname) values(? , ? , ? , ?)";
	public static final String PAYMENT_ONLINE = "insert into payment_online(referenceid, card, cardnum, cardtype, modeoftransfer, accountnumber, ifscode) values(? , ? , ? , ? , ? , ? , ?)";
	public static final String PAYMENT = "insert into payment(referenceid , studentid , mode, status , amount) values(? , ? , ? , ? , ?)";
    public static final String STUDENT_ADD_COURSE = "insert into registeredcourse (coursecode, studentId, grade, type) values(?,?,?,?)";

}
