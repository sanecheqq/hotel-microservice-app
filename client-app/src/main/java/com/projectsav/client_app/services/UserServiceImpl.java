package com.projectsav.client_app.services;

import com.projectsav.client_app.messages.users.GetUserDataResponse;
import com.projectsav.client_app.model.User;
import com.projectsav.client_app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public GetUserDataResponse getUserData(String login) {
        User user = userRepository.findByLogin(login).orElseThrow(NoSuchElementException::new);
        return null;
    }
}
