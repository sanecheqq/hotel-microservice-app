package com.projectsav.client_app.services;

import com.projectsav.client_app.messages.auth.AuthRequest;
import com.projectsav.client_app.messages.auth.AuthResponse;
import com.projectsav.client_app.model.User;
import com.projectsav.client_app.repositories.UserRepository;
import com.projectsav.client_app.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

//    private final UserResolveService userResolveService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthResponse authUser(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword()));
        User user = userRepository.findByLogin(authRequest.getLogin()).orElseThrow(NoSuchElementException::new);
        return generateUserAccessTokens(user);
    }

    private AuthResponse generateUserAccessTokens(User user) {
        return new AuthResponse(jwtUtil.generateAccessToken(user.getLogin()));
    }
}
