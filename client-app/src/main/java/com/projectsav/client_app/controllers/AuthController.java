package com.projectsav.client_app.controllers;

import com.projectsav.client_app.messages.auth.AuthRequest;
import com.projectsav.client_app.messages.auth.AuthResponse;
import com.projectsav.client_app.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authorizationService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authUser(
            @RequestBody @Valid AuthRequest authRequest
    ) {
        AuthResponse response = authorizationService.authUser(authRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
