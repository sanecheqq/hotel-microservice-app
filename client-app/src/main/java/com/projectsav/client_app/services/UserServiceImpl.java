package com.projectsav.client_app.services;

import com.projectsav.client_app.exceptions.NoSuchUserException;
import com.projectsav.client_app.messages.dtos.UserDto;
import com.projectsav.client_app.model.User;
import com.projectsav.client_app.repositories.UserRepository;
import com.projectsav.client_app.util.converters.UserToUserDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserToUserDtoConverter userToUserDtoConverter;

    @Override
    public UserDto getUserData(String login) {
        User user = userRepository.findByLogin(login).orElseThrow(() -> new NoSuchUserException("No user " + login));
        return userToUserDtoConverter.convert(user);
    }
}
