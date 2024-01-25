package com.projectsav.bookingapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "hotel_rooms")
public class Room {
    @Id
    @GeneratedValue
    private Long number; // room number - unique value

    @Column(name = "price")
    private float price;

    @Column(name = "type")
    private RoomType type;
}


