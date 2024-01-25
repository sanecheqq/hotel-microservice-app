package com.projectsav.client_app.services;

import com.projectsav.client_app.messages.dtos.UserDto;

public interface UserService {
    UserDto getUserData(String id);
}
