package com.hotelbooking.model;

public enum roomType {
    SINGLE(1),
    DOUBLE(2),
    SUITE(3);

    private final int maxGuests;

    roomType(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    public int getMaxGuests() {
        return maxGuests;
    }
}
