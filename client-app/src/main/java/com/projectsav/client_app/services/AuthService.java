package com.projectsav.client_app.services;

import com.projectsav.client_app.messages.auth.AuthResponse;
import com.projectsav.client_app.messages.auth.AuthRequest;

public interface AuthService {
    AuthResponse authUser(AuthRequest authRequest);
}
