package com.projectsav.bookingapp.messages.dtos;

import com.projectsav.bookingapp.model.Room;

public record BookingDto(String id, Room room, String start, String end, String cliendId) {
}
