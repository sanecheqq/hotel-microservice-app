package com.projectsav.bookingapp.services;

import com.projectsav.bookingapp.exceptions.RoomNotFoundException;
import com.projectsav.bookingapp.messages.rooms.GetAllRoomsResponse;
import com.projectsav.bookingapp.messages.rooms.GetAvailableRoomsResponse;
import com.projectsav.bookingapp.model.Room;
import com.projectsav.bookingapp.model.RoomType;
import com.projectsav.bookingapp.model.SortRoomsType;
import com.projectsav.bookingapp.repositories.RoomRepository;
import com.projectsav.bookingapp.util.converters.RoomToRoomDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomToRoomDtoConverter roomToRoomDtoConverter;

    @Override
    public Room getRoomByNumber(Long number) {
        return roomRepository.findById(number)
                .orElseThrow(() -> new RoomNotFoundException("No room entity with number " + number));
    }

    @Override
    public GetAllRoomsResponse getAllRooms() {
        List<Room> rooms = roomRepository.findAll(Sort.by("number"));
        return new GetAllRoomsResponse(
                rooms
                        .stream()
                        .map(roomToRoomDtoConverter::convert)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public GetAvailableRoomsResponse getAvailableRooms(
            LocalDateTime start,
            LocalDateTime end,
            RoomType roomType,
            Float maxPrice,
            Float minPrice,
            SortRoomsType sortRoomsType
    ) {
        List<Room> availableRooms;
        if (sortRoomsType == null) {
            availableRooms = roomRepository.getAvailableRoomsByRoomTypeAndMaxPriceAndMinPriceAndStartAndEndDates(
                    roomType,
                    maxPrice,
                    minPrice,
                    Timestamp.valueOf(start),
                    Timestamp.valueOf(end)
            );
        } else {
            availableRooms = roomRepository.getAvailableRoomsByRoomTypeAndMaxPriceAndMinPriceAndStartAndEndDatesAndSort(
                    roomType,
                    maxPrice,
                    minPrice,
                    Timestamp.valueOf(start),
                    Timestamp.valueOf(end),
                    Sort.by(sortRoomsType.getSortType())
            );
        }

        return new GetAvailableRoomsResponse(
                availableRooms
                        .stream()
                        .map(roomToRoomDtoConverter::convert)
                        .collect(Collectors.toList())
        );
    }

}
