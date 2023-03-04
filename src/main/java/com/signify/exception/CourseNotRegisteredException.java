package com.signify.exception;

public class CourseNotRegisteredException extends Exception {

    @Override
    public String getMessage() {
        return "Course Not Registered by student";
    }

}
