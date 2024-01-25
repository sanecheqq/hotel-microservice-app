package com.projectsav.bookingapp.repositories;

import com.projectsav.bookingapp.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
