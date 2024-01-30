package com.projectsav.bookingapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotel_rooms")
public class Room {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long number; // room number - unique value

    @Column(name = "price")
    private float price;

    @Column(name = "type")
    private RoomType type;

    @OneToMany(mappedBy = "room")
    private List<Booking> bookings = new ArrayList<>();
}


