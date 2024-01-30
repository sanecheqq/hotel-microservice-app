package com.projectsav.bookingapp.external.services;

import com.projectsav.bookingapp.external.messages.dtos.UserDto;

public interface AuthService {
    UserDto getUserDataFromClientApp(String authHeader);
}
