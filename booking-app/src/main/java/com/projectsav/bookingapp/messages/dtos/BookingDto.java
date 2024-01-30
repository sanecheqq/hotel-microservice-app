package com.projectsav.bookingapp.messages.dtos;

public record BookingDto(String id, RoomDto room, String start, String end, String cliendId) {
}
