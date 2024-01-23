package com.projectsav.client_app.configuration;

import com.projectsav.client_app.model.Role;
import com.projectsav.client_app.model.User;
import com.projectsav.client_app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class DataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        // TODO: change to liquibase migration
        if (userRepository.count() == 0) {
            fillDatabase();
        }
    }

    private void fillDatabase() {
        User userUser = new User(null, "user", "pipi pupu", "23/09/2002", "1234", "abc@yandex.ru",
                passwordEncoder.encode("user"), Role.USER);
        userRepository.saveAll(List.of(userUser));
    }
}
