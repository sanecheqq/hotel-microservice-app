package com.projectsav.bookingapp.repositories;

import com.projectsav.bookingapp.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, String> {
    @Query("SELECT b FROM Booking b WHERE b.room.number = ?1 " +
            "and ((b.start <= ?2 and b.end >= ?2) or (b.start <= ?3 and b.end >= ?3) or (?2 <= b.start and b.end <= ?3))")
    /*
         1. (b.start <= ?2 and b.end >= ?2) - case when incoming 'start' between existing booking dates - b.start and b.end
         2. (b.start <= ?3 and b.end >= ?3) - case when incoming 'end' between existing booking dates - b.start and b.end
         3. (?2 <= b.start and b.end <= ?3) - case when existing booking dates are between incoming start and end:
         visually: [   (     )     ] - ?2 - Timestamp start, ?3 - Timestamp end, b.s and b.e - b.start and b.end
                  ?2   b.s   b.e  ?3
     */
    List<Booking> getRoomsWithIntersectingDate(Long roomNumber, Timestamp start, Timestamp end);
}
