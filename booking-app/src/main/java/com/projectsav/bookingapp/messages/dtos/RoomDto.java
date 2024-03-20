package com.projectsav.bookingapp.messages.dtos;

public record RoomDto(
        Long number,
        float price,
        String type
) {
}
