package com.projectsav.client_app.messages.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projectsav.client_app.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;
    private String login;
    @JsonProperty("password_hash")
    private String passwordHash;
    private String email;
    @JsonProperty("full_name")
    private String fullName;
    private Role role;
}
