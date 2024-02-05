package com.projectsav.bookingapp.messages.rooms;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projectsav.bookingapp.model.Room;

import java.util.List;

public record GetAllRoomsResponse(@JsonProperty("hotel_rooms") List<Room> hotelRooms) {
}
