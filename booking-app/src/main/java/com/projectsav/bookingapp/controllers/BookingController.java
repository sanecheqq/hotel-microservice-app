package com.projectsav.bookingapp.controllers;

import com.projectsav.bookingapp.messages.dtos.BookingDto;
import com.projectsav.bookingapp.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    @PostMapping("/{room_number}")
    public ResponseEntity<BookingDto> bookRoom(
            @PathVariable(name = "room_number") Long roomNumber,
            @RequestParam(name = "start", defaultValue = "01/01/24 00:00:00") Timestamp startDate,
            @RequestParam(name = "end", defaultValue = "01/01/24 00:00:00") Timestamp endDate,
            @RequestHeader("Authorization") String authHeader
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(new BookingDto());
    }
}
