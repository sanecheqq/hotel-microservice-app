package com.projectsav.bookingapp.messages.dtos;

public record UserDto(String id, String login, String passwordHash,
                      String email, String fullName, String role) {
}
