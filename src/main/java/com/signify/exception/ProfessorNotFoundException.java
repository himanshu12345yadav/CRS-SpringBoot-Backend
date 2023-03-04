package com.signify.exception;

public class ProfessorNotFoundException extends Exception {
	private String professor;

	public ProfessorNotFoundException() {

	}

	public ProfessorNotFoundException(String professor) {
		this.professor = professor;
	}

	@Override
	public String getMessage() {
		return "Professor Not Found";
	}
}
