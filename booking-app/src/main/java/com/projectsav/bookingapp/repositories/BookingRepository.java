package com.projectsav.bookingapp.repositories;

import com.projectsav.bookingapp.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, String> {
    /*
         1-2. - simple cases when provided start or end get between b.start and b.end;
         3. (:start <= b.start and b.end <= :end) - case when existing booking dates are between incoming start and end:
         visually: [      (       )       ] - b.s and b.e are b.start and b.end
                 :start   b.s    b.e    :end
     */
    @Query(value = """
        SELECT b FROM Booking b
        WHERE b.room.number = :roomNum
            AND (
                (:start BETWEEN b.start AND b.end)
                OR (:end BETWEEN b.start AND b.end)
                OR (:start <= b.start AND b.end <= :end)
            )
    """)
    List<Booking> getRoomsWithIntersectingDate(
            @Param("roomNum") Long roomNumber,
            @Param("start") Timestamp start,
            @Param("end") Timestamp end
    );
}
