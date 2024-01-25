package com.projectsav.bookingapp.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthorizationAspect {

    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;


    @Before("execution(* com.projectsav.bookingapp.controllers.*.book(..))")
    public void checkAuth2() {
        System.out.println("Catch request...");

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String authorization = request.getHeader("Authorization");

        System.out.println("Header: " + authorization);

        ServiceInstance backendInstance = discoveryClient.getInstances("client-backend").get(0);
        URI pingUri = backendInstance.getUri().resolve("/users/user");

        System.out.println("Sending request to " + pingUri);

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", authorization));
        restTemplate.setInterceptors(interceptors);
        String answer = restTemplate.getForObject(pingUri, String.class);

        System.out.println("Get answer: " + answer);
    }
}
