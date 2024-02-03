package com.projectsav.bookingapp.repositories;

import com.projectsav.bookingapp.model.Room;
import com.projectsav.bookingapp.model.RoomType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    /*
        Find all available rooms which can be booked on provided start and end dates (from start to end).
        Verification condition are the same as in the BookingRepository, but also we need to make left join with booking table
        to check all bookings for current room.
     */
    @Query("SELECT DISTINCT r FROM Room r LEFT JOIN r.bookings WHERE " +
        "NOT EXISTS (" +
            "SELECT 1 FROM Booking b WHERE " +
                "b.room = r AND (" +
                        "   (:start BETWEEN b.start AND b.end) " +
                        "OR (:end BETWEEN b.start AND b.end) " +
                        "OR (:start <= b.start AND b.end <= :end)" +
                ")" +
        ")"
    )
    List<Room> getAvailableRoomsByStartAndEndDatesAndSort(
            @Param("start") Timestamp start,
            @Param("end") Timestamp end,
            Sort sort
    );


    @Query("SELECT DISTINCT r FROM Room r LEFT JOIN r.bookings WHERE " +
            "r.type = :room_type " +
            "AND NOT EXISTS (" +
                "SELECT 1 FROM Booking b WHERE " +
                "b.room = r AND (" +
                    "   (:start BETWEEN b.start AND b.end) " +
                    "OR (:end BETWEEN b.start AND b.end) " +
                    "OR (:start <= b.start AND b.end <= :end)" +
                ")" +
            ")"
    )
    List<Room> getAvailableRoomsByStartAndEndDatesAndRoomTypeAndSort(
            @Param("start") Timestamp start,
            @Param("end") Timestamp end,
            @Param("room_type") RoomType room_type,
            Sort sort
    );
}
