package com.signify.exception;

public class CourseNotAssignedException extends Exception {

    @Override
    public String getMessage() {
        return "Invalid Professor or Course id";
    }
}
