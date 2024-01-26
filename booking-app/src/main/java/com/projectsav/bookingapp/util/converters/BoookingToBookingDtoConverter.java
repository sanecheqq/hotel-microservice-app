package com.projectsav.bookingapp.util.converters;

import com.projectsav.bookingapp.messages.dtos.BookingDto;
import com.projectsav.bookingapp.model.Booking;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BoookingToBookingDtoConverter implements Converter<Booking, BookingDto> {
    @Override
    public BookingDto convert(Booking booking) {
        return new BookingDto(
                booking.getId(),
                booking.getRoom(),
                booking.getStart().toString(),
                booking.getEnd().toString(), booking.getClientId()
        );
    }
}
