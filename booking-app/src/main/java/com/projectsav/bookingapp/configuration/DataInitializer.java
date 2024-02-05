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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        Random random = new Random();
        List<Room> rooms = new ArrayList<>();
        for (long i = 0; i < 10; i++) {
            rooms.add(new Room(200 + i, random.nextFloat(300), RoomType.STANDARD, new ArrayList<>()));
        }

        for (long i = 5; i < 10; i++) {
            rooms.add(new Room(300 + i, random.nextFloat(400), RoomType.BUSINESS, new ArrayList<>()));
        }
        roomRepository.saveAll(rooms);
    }
}
