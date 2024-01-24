package com.projectsav.client_app.controllers;

import com.projectsav.client_app.messages.dtos.UserDto;
import com.projectsav.client_app.security.CustomUserDetails;
import com.projectsav.client_app.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user/{user_login}")
    public ResponseEntity<UserDto> getUserData(
            @PathVariable(name = "user_login") String login
    ) {
        UserDto userData = userService.getUserData(login);
        return ResponseEntity.status(HttpStatus.OK).body(userData);
    }

    @GetMapping("/user")
    public String getUserByToken(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return userDetails.getUsername();
    }

}
