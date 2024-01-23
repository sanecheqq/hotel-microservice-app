package com.projectsav.client_app.controllers;

import com.projectsav.client_app.messages.dtos.UserDto;
import com.projectsav.client_app.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{user_id}")
    public ResponseEntity<UserDto> getUserData(
            @PathVariable(name = "user_id") String userId
    ) {
        UserDto userData = userService.getUserData(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userData);
    }

}
