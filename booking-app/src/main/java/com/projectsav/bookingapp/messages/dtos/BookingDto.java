package com.projectsav.bookingapp.messages.dtos;

public record BookingDto(String bookingId, RoomDto room, String start, String end, String clientId) {
}
