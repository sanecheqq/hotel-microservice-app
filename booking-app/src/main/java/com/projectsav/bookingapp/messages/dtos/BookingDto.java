package com.projectsav.bookingapp.messages.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BookingDto(
        @JsonProperty("booking_id")
        String bookingId,
        RoomDto room,
        String start,
        String end,
        @JsonProperty("client_id")
        String clientId

) {
}
