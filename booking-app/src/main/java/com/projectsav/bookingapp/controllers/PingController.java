package com.projectsav.bookingapp.controllers;

import com.projectsav.bookingapp.external.messages.dtos.UserDto;
import com.projectsav.bookingapp.external.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PingController {
    private final AuthService authService;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("/auth:ping")
    public String authPing(
            @RequestHeader("Authorization") String authHeader
    ) {
        UserDto userData = authService.getUserDataFromClientApp(authHeader);
        System.out.println(userData);
        return "auth-pong";
    }

}
