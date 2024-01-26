package com.projectsav.bookingapp.exceptions;

public class RoomIsAlreadyBookedException extends RuntimeException {
    public RoomIsAlreadyBookedException(String message) {
        super(message);
    }
}
