package com.projectsav.client_app.services;

import com.projectsav.client_app.messages.users.GetUserDataResponse;

public interface UserService {
    GetUserDataResponse getUserData(String login);
}
