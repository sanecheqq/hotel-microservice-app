package com.projectsav.bookingapp.services;

import com.projectsav.bookingapp.messages.dtos.BookingDto;

import java.sql.Timestamp;

public interface BookingService {
    BookingDto bookRoom(Long roomNumber, Timestamp startDate, Timestamp endDate, String clientId);
}
