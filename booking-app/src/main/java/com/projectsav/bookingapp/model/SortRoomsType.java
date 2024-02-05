package com.projectsav.bookingapp.model;

import lombok.Getter;

@Getter
public enum SortRoomsType {
    PRICE("price"),
    TYPE("type"),
    NUMBER("number");

    private final String sortType;

    SortRoomsType(String type) {
        this.sortType = type;
    }
}
