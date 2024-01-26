package com.projectsav.bookingapp.services;

import com.projectsav.bookingapp.messages.dtos.UserDto;

public interface AuthService {
    UserDto getUserData(String authHeader);
}
