package com.projectsav.client_app.util.converters;

import com.projectsav.client_app.messages.dtos.UserDto;
import com.projectsav.client_app.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoConverter implements Converter<User, UserDto> {
    @Override
    public UserDto convert(User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getEmail(),
                user.getFullName(),
                user.getRole()
        );
        return userDto;
    }
}
