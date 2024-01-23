package com.projectsav.client_app.controllers;

import com.projectsav.client_app.messages.users.GetUserDataResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/users")
public class UserController {
    @GetMapping("/{user_id}")
    public ResponseEntity<GetUserDataResponse> getUserData(
            @PathVariable(name = "user_id") String userId
    ) {

//        return ResponseEntity.status(HttpStatus.OK).body();
        return null;
    }

}
