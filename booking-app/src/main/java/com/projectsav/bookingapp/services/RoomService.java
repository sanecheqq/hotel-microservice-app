package com.projectsav.bookingapp.services;

import com.projectsav.bookingapp.messages.rooms.GetAllRoomsResponse;
import com.projectsav.bookingapp.model.Room;

public interface RoomService {

    Room getRoomByNumber(Long number);

    GetAllRoomsResponse getAllRooms();
}
