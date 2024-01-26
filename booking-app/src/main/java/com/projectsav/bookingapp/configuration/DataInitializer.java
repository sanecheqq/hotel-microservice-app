package com.projectsav.bookingapp.configuration;

import com.projectsav.bookingapp.model.Room;
import com.projectsav.bookingapp.model.RoomType;
import com.projectsav.bookingapp.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class DataInitializer implements ApplicationRunner {

//    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        if (roomRepository.count() == 0) {
            fillDatabase();
        }
    }

    private void fillDatabase() {
        Room room1 = new Room(201L, 100, RoomType.BUSINESS);
        roomRepository.save(room1);
    }
}
