package com.projectsav.bookingapp.services;

import com.projectsav.bookingapp.messages.dtos.BookingDto;
import com.projectsav.bookingapp.external.messages.dtos.UserDto;

import java.time.LocalDateTime;

public interface BookingService {
    BookingDto bookRoom(Long roomNumber, LocalDateTime startDate, LocalDateTime endDate, UserDto clientId);

    void deleteBookingRoom(String bookingId, UserDto userDto);

    BookingDto changeBookingRoomNumber(String bookingId, Long newRoomNumber, UserDto userDto);
}
