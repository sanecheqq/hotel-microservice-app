package com.projectsav.bookingapp.controllers;

import com.projectsav.bookingapp.messages.dtos.BookingDto;
import com.projectsav.bookingapp.messages.dtos.UserDto;
import com.projectsav.bookingapp.services.AuthService;
import com.projectsav.bookingapp.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {
    private final AuthService authService;
    private final BookingService bookingService;

    @PostMapping("/{room_number}")
    public ResponseEntity<BookingDto> bookRoom(
            @PathVariable(name = "room_number") Long roomNumber,
            @RequestParam(name = "start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(name = "end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestHeader("Authorization") String authHeader
    ) {
        UserDto userData = authService.getUserData(authHeader);
        BookingDto bookingDto = bookingService.bookRoom(roomNumber, startDate, endDate, userData);
        return ResponseEntity.status(HttpStatus.OK).body(bookingDto);
    }
}
