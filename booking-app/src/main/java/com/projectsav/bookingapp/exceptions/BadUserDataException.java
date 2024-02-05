package com.projectsav.bookingapp.exceptions;

public class BadUserDataException extends RuntimeException {
    public BadUserDataException(String message) {
        super(message);
    }
}
