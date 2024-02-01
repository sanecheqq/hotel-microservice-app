package com.projectsav.bookingapp.services;

import com.projectsav.bookingapp.exceptions.BookingNotFoundException;
import com.projectsav.bookingapp.exceptions.RoomIsAlreadyBookedException;
import com.projectsav.bookingapp.exceptions.RoomNotFoundException;
import com.projectsav.bookingapp.exceptions.UserIdDifferFromBookingClientIdException;
import com.projectsav.bookingapp.external.messages.dtos.UserDto;
import com.projectsav.bookingapp.messages.dtos.BookingDto;
import com.projectsav.bookingapp.model.Booking;
import com.projectsav.bookingapp.model.Room;
import com.projectsav.bookingapp.repositories.BookingRepository;
import com.projectsav.bookingapp.repositories.RoomRepository;
import com.projectsav.bookingapp.util.converters.BoookingToBookingDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    private final RoomService roomService;

    private final BoookingToBookingDtoConverter boookingToBookingDtoConverter;
    @Override
    public BookingDto bookRoom(Long roomNumber, LocalDateTime startDate, LocalDateTime endDate, UserDto userDto) {
        checkThatRoomIsAvailable(roomNumber, startDate, endDate);

        Room room = roomRepository.findById(roomNumber)
                .orElseThrow(() -> new RoomNotFoundException("No room entity with number " + roomNumber));

        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setStart(Timestamp.valueOf(startDate));
        booking.setEnd(Timestamp.valueOf(endDate));
        booking.setClientId(userDto.id());

        booking = bookingRepository.save(booking);
        return boookingToBookingDtoConverter.convert(booking);
    }

    @Override
    public void deleteBookingRoom(String bookingId, UserDto userDto) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("No booking entity with id " + bookingId));
        if (!booking.getClientId().equals(userDto.id())) {
            throw new UserIdDifferFromBookingClientIdException("User id " + userDto.id() + " does not match with " + booking.getClientId());
        }

        bookingRepository.delete(booking);
    }

    public void checkThatRoomIsAvailable(Long roomNumber,LocalDateTime startDate,LocalDateTime endDate) {
        List<Booking> rooms = bookingRepository.getRoomsWithIntersectingDate(roomNumber, Timestamp.valueOf(startDate), Timestamp.valueOf(endDate));
        if (!rooms.isEmpty()) {
            throw new RoomIsAlreadyBookedException("Provided booking dates are wrong");
        }
    }
}
