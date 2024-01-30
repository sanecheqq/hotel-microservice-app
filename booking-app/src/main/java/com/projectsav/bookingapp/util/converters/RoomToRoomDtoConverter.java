package com.projectsav.bookingapp.util.converters;

import com.projectsav.bookingapp.messages.dtos.RoomDto;
import com.projectsav.bookingapp.model.Room;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoomToRoomDtoConverter implements Converter<Room, RoomDto> {
    @Override
    public RoomDto convert(Room room) {
        return new RoomDto(
                room.getNumber(),
                room.getPrice(),
                room.getType().toString()
        );
    }
}
