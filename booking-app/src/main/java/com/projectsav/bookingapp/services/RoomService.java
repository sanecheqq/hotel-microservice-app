package com.projectsav.bookingapp.services;

import com.projectsav.bookingapp.messages.rooms.GetAllRoomsResponse;
import com.projectsav.bookingapp.messages.rooms.GetAvailableRoomsResponse;
import com.projectsav.bookingapp.model.Room;
import com.projectsav.bookingapp.model.RoomType;
import com.projectsav.bookingapp.model.SortRoomsType;

import java.time.LocalDateTime;

public interface RoomService {

    Room getRoomByNumber(Long number);

    GetAllRoomsResponse getAllRooms();

    GetAvailableRoomsResponse getAvailableRooms(
            LocalDateTime start,
            LocalDateTime end,
            RoomType roomType,
            Float maxPrice,
            Float minPrice,
            SortRoomsType sortRoomsType
    );
}
