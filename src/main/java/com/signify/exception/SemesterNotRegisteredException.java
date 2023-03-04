package com.signify.exception;

public class SemesterNotRegisteredException extends Exception {
    private String studentId;

    public SemesterNotRegisteredException(String studentId){
        this.studentId = studentId;
    }

    @Override
    public String getMessage(){
        return "Student Not Registered for the Semester";
    }
}
