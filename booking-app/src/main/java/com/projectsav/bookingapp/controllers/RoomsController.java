package com.projectsav.bookingapp.controllers;

import com.projectsav.bookingapp.messages.rooms.GetAllRoomsResponse;
import com.projectsav.bookingapp.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomsController {
    private final RoomService roomService;

    @GetMapping("/get-all")
    public ResponseEntity<GetAllRoomsResponse> getAllRooms() {
        GetAllRoomsResponse response = roomService.getAllRooms();
        return ResponseEntity.ok(response);
    }
}
