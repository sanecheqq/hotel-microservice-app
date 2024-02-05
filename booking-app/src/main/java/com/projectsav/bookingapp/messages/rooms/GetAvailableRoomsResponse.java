package com.projectsav.bookingapp.messages.rooms;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projectsav.bookingapp.messages.dtos.RoomDto;

import java.util.List;

public record GetAvailableRoomsResponse(@JsonProperty("available_rooms") List<RoomDto> availableRooms) {
}
