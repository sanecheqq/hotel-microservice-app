package com.projectsav.bookingapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "booking")
@ToString
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "number")
    @ToString.Exclude
    private Room room;

    @Column(name = "booking_start")
    private Timestamp start;

    @Column(name = "booking_end")
    private Timestamp end;

    @Column(name = "client_id")
    private String clientId;

}
