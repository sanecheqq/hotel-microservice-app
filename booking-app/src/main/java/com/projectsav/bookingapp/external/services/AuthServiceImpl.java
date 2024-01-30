package com.projectsav.bookingapp.external.services;

import com.projectsav.bookingapp.util.HeaderRequestInterceptor;
import com.projectsav.bookingapp.external.messages.dtos.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;

    @Override
    public UserDto getUserDataFromClientApp(String authHeader) {
        ServiceInstance backendInstance = discoveryClient.getInstances("client-backend").get(0);
        URI getUserByTokenUri = backendInstance.getUri().resolve("/users/user");

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", authHeader));
        restTemplate.setInterceptors(interceptors);
        return restTemplate.getForObject(getUserByTokenUri, UserDto.class);
    }
}
