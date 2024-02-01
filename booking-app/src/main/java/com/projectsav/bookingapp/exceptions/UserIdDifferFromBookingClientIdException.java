package com.projectsav.bookingapp.exceptions;

public class UserIdDifferFromBookingClientIdException extends RuntimeException {
    public UserIdDifferFromBookingClientIdException(String message) {
        super(message);
    }
}
