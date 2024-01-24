package com.projectsav.client_app.security;

import lombok.Getter;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class CustomUserDetails extends User {
    private final String id;

    public CustomUserDetails(String username, String password, String id) {
        super(username, password, List.of());
        this.id = id;
    }

}
