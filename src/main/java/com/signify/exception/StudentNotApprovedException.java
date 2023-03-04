package com.signify.exception;

public class StudentNotApprovedException extends Exception {
    private String studentId;

    public StudentNotApprovedException(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public String getMessage() {
        return "Student Not Approved Yet , Ask admin for Approval";
    }
}
