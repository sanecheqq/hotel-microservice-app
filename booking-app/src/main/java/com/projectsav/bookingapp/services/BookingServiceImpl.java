package com.projectsav.bookingapp.services;

import com.projectsav.bookingapp.messages.dtos.BookingDto;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class BookingServiceImpl implements BookingService {
    @Override
    public BookingDto bookRoom(Long roomNumber, Timestamp startDate, Timestamp endDate, String clientId) {
        return null;
    }
}
