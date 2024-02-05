package com.projectsav.bookingapp.services;

import com.projectsav.bookingapp.exceptions.RoomNotFoundException;
import com.projectsav.bookingapp.messages.rooms.GetAllRoomsResponse;
import com.projectsav.bookingapp.model.Room;
import com.projectsav.bookingapp.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    @Override
    public Room getRoomByNumber(Long number) {
        return roomRepository.findById(number)
                .orElseThrow(() -> new RoomNotFoundException("No room entity with number " + number));
    }

    @Override
    public GetAllRoomsResponse getAllRooms() {
        List<Room> rooms = roomRepository.findAll(Sort.by("number"));
        return new GetAllRoomsResponse(rooms);
    }
}
