package com.projectsav.client_app.services;

import com.projectsav.client_app.messages.auth.AuthRequest;
import com.projectsav.client_app.messages.auth.AuthResponse;

public interface AuthService {
    AuthResponse authUser(AuthRequest authRequest);
}
