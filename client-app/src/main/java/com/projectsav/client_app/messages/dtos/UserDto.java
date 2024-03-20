package com.projectsav.client_app.messages.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projectsav.client_app.model.Role;

public record UserDto(
        String id,
        String login,
        @JsonProperty("password_hash")
        String passwordHash,
        String email,
        String fullName,
        Role role
) {
}
