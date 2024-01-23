package com.projectsav.client_app.messages.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotBlank
    private String login;

    @NotBlank
    private String password;

    public void setLogin(String login) {
        this.login = login.toLowerCase();
    }

    public void setPassword (String password) {
        this.password = password;
    }
}
