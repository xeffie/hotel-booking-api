package com.hotelbooking.model;

public enum RoomType {
    SINGLE(1),
    DOUBLE(2),
    SUITE(3);

    private final int maxGuests;

    RoomType(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    public int getMaxGuests() {
        return maxGuests;
    }
}
