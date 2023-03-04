package com.signify.exception;

public class ProfessorNotAssignedException extends Exception {
	String professorId;
	
	public ProfessorNotAssignedException(String professorId) {
		this.professorId = professorId;
	}
	@Override
	public String getMessage() {
		return "Professor Not Assigned to Any Course";
	}
}
