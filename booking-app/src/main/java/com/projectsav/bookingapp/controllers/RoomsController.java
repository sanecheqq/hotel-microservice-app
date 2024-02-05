package com.projectsav.bookingapp.controllers;

import com.projectsav.bookingapp.messages.rooms.GetAllRoomsResponse;
import com.projectsav.bookingapp.messages.rooms.GetAvailableRoomsResponse;
import com.projectsav.bookingapp.model.RoomType;
import com.projectsav.bookingapp.model.SortRoomsType;
import com.projectsav.bookingapp.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomsController {
    private final RoomService roomService;

    @GetMapping("/all")
    public ResponseEntity<GetAllRoomsResponse> getAllRooms() {
        GetAllRoomsResponse response = roomService.getAllRooms();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/available")
    public ResponseEntity<GetAvailableRoomsResponse> getAvailableRooms(
            @RequestParam(name = "start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(name = "end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(name = "room_type", required = false) RoomType roomType,
            @RequestParam(name = "max_price", required = false) Float maxPrice,
            @RequestParam(name = "min_price", required = false) Float minPrice,
            @RequestParam(name = "sort", required = false) SortRoomsType sortRoomsType
    ) {

        return ResponseEntity.ok(roomService.getAvailableRooms(startDate, endDate, roomType, maxPrice, minPrice, sortRoomsType));
    }
}
