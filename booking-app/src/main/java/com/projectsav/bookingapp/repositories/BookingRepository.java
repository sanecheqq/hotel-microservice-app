package com.projectsav.bookingapp.repositories;

import com.projectsav.bookingapp.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, String> {
}
