package com.projectsav.bookingapp.services;

import com.projectsav.bookingapp.messages.dtos.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;

    @Override
    public UserDto getUserData(String jwt) {
        //TODO: realize sending request to client-app with bearer token and get userDto.
        return null;
    }
}
