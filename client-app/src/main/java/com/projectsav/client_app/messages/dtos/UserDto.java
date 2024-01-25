package com.projectsav.client_app.messages.dtos;

import com.projectsav.client_app.model.Role;

public record UserDto(String id, String login, String passwordHash,
                      String email, String fullName, Role role) {
}
