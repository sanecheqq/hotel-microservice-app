package com.projectsav.bookingapp.services;

import com.projectsav.bookingapp.exceptions.BadDatesParametersException;
import com.projectsav.bookingapp.exceptions.BadUserDataException;
import com.projectsav.bookingapp.exceptions.BookingNotFoundException;
import com.projectsav.bookingapp.exceptions.RoomIsAlreadyBookedException;
import com.projectsav.bookingapp.external.messages.dtos.UserDto;
import com.projectsav.bookingapp.messages.dtos.BookingDto;
import com.projectsav.bookingapp.model.Booking;
import com.projectsav.bookingapp.model.Room;
import com.projectsav.bookingapp.repositories.BookingRepository;
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
    private final RoomService roomService;
    private final BoookingToBookingDtoConverter boookingToBookingDtoConverter;


    @Override
    public BookingDto bookRoom(Long roomNumber, LocalDateTime startDate, LocalDateTime endDate, UserDto userDto) {
        if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
            throw new BadDatesParametersException("Start date " + startDate + "  must be less than end " + endDate);
        }

        checkThatRoomIsAvailable(roomNumber, startDate, endDate);

        Room room = roomService.getRoomByNumber(roomNumber);

        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setStart(Timestamp.valueOf(startDate));
        booking.setEnd(Timestamp.valueOf(endDate));
        booking.setClientId(userDto.id());

        booking = saveBooking(booking);
        return boookingToBookingDtoConverter.convert(booking);
    }

    @Override
    public void deleteBookingRoom(String bookingId, UserDto userDto) {
        Booking booking = getBooking(bookingId);

        checkThatClientIsCorrect(booking, userDto);

        bookingRepository.delete(booking);
    }

    @Override
    public BookingDto changeBookingRoomNumber(String bookingId, Long newRoomNumber, UserDto userDto) {
        Booking booking = getBooking(bookingId);

        checkThatRoomIsAvailable(newRoomNumber, booking.getStart().toLocalDateTime(), booking.getEnd().toLocalDateTime());
        checkThatClientIsCorrect(booking, userDto);

        booking.setRoom(roomService.getRoomByNumber(newRoomNumber));
        saveBooking(booking);
        return boookingToBookingDtoConverter.convert(booking);
    }

    @Override
    public BookingDto changeBookingDates(String bookingId, LocalDateTime startDate, LocalDateTime endDate, UserDto userDto) {
        Booking booking = getBooking(bookingId);

        checkThatClientIsCorrect(booking, userDto);

        LocalDateTime newStartDate = startDate == null ? booking.getStart().toLocalDateTime() : startDate;
        LocalDateTime newEndDate = endDate == null ? booking.getEnd().toLocalDateTime() : endDate;

        if (newStartDate.isAfter(newEndDate) || newStartDate.isEqual(newEndDate)) {
            throw new BadDatesParametersException("Start date " + startDate + "  must be less than end " + endDate);
        }

        checkThatChangingDatesIsPossible(booking, newStartDate, newEndDate);

        booking.setStart(Timestamp.valueOf(newStartDate));
        booking.setEnd(Timestamp.valueOf(newEndDate));
        saveBooking(booking);
        return boookingToBookingDtoConverter.convert(booking);
    }

    private void checkThatClientIsCorrect(Booking booking, UserDto userDto) {
        if (!booking.getClientId().equals(userDto.id())) {
            throw new BadUserDataException("User id " + userDto.id() + " does not match with " + booking.getClientId());
        }
    }

    private void checkThatRoomIsAvailable(Long roomNumber, LocalDateTime startDate, LocalDateTime endDate) {
        List<Booking> bookings = bookingRepository.getRoomsWithOvelappingDates(roomNumber, Timestamp.valueOf(startDate), Timestamp.valueOf(endDate));
        if (!bookings.isEmpty()) {
            throw new RoomIsAlreadyBookedException("The booking dates overlap");
        }
    }

    private void checkThatChangingDatesIsPossible(Booking booking, LocalDateTime startDate, LocalDateTime endDate) {
        List<Booking> bookings = bookingRepository.getRoomsWithOvelappingDates(booking.getRoom().getNumber(), Timestamp.valueOf(startDate), Timestamp.valueOf(endDate));
        if (bookings.size() == 1 && bookings.get(0).getId().equals(booking.getId())) {
            return;
        }
        if (!bookings.isEmpty()) {
            throw new RoomIsAlreadyBookedException("The booking dates overlap");
        }
    }

    public Booking getBooking(String bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("No booking entity with id " + bookingId));
    }

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
}
