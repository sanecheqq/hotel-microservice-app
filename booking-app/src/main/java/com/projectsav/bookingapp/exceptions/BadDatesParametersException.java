package com.projectsav.bookingapp.exceptions;

public class BadDatesParametersException extends RuntimeException {
    public BadDatesParametersException(String message) {
        super(message);
    }
}
