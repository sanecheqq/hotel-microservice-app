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

    /**
        Finds all available rooms which can be booked on provided start and end dates (from start to end),
        also the type and max-min prices parameters are taken into account.
        Verification condition are the same as in the BookingRepository, but also we need to make left join with booking table
        to check all bookings for current room.
     */
    @Query(value = """
        SELECT DISTINCT r FROM Room r LEFT JOIN r.bookings
        WHERE (:room_type IS NULL OR :room_type = r.type)
            AND (:max_price IS NULL OR r.price <= :max_price)
            AND (:min_price IS NULL OR r.price >= :min_price)
            AND NOT EXISTS (
                SELECT 1 FROM Booking b
                WHERE b.room = r
                    AND (
                        (:start BETWEEN b.start AND b.end)
                        OR (:end BETWEEN b.start AND b.end)
                        OR (:start <= b.start AND b.end <= :end)
                    )
            )
    """)
    List<Room> getAvailableRoomsByRoomTypeAndMaxPriceAndMinPriceAndStartAndEndDatesAndSort(
            @Param("room_type") RoomType roomType,
            @Param("max_price") Float maxPrice,
            @Param("min_price") Float minPrice,
            @Param("start") Timestamp start,
            @Param("end") Timestamp end,
            Sort sort
    );

    /*
        WITHOUT SORT
     */
    @Query(value = """
        SELECT DISTINCT r FROM Room r LEFT JOIN r.bookings
        WHERE (:room_type IS NULL OR :room_type = r.type)
            AND (:max_price IS NULL OR r.price <= :max_price)
            AND (:min_price IS NULL OR r.price >= :min_price)
            AND NOT EXISTS (
                SELECT 1 FROM Booking b
                WHERE b.room = r
                    AND (
                        (:start BETWEEN b.start AND b.end)
                        OR (:end BETWEEN b.start AND b.end)
                        OR (:start <= b.start AND b.end <= :end)
                    )
            )
    """)
    List<Room> getAvailableRoomsByRoomTypeAndMaxPriceAndMinPriceAndStartAndEndDates(
            @Param("room_type") RoomType roomType,
            @Param("max_price") Float maxPrice,
            @Param("min_price") Float minPrice,
            @Param("start") Timestamp start,
            @Param("end") Timestamp end
    );
}
