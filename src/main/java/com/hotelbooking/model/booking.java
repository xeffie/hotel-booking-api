package com.hotelbooking.model;

import java.time.Instant;
import java.util.UUID;

public class booking {
    private final String bookingNumber; // externt id
    private final String guestName;
    private final String guestEmail;
    private final int guests;
    private final RoomType roomType;
    private final int nights;
    private final Instant createdAt;

    public booking(String guestName, String guestEmail, int guests, RoomType roomType, int nights) {
        this.bookingNumber = UUID.randomUUID().toString();
        this.guestName = guestName;
        this.guestEmail = guestEmail;
        this.guests = guests;
        this.roomType = roomType;
        this.nights = nights;
        this.createdAt = Instant.now();
    }

    public String getBookingNumber() { return bookingNumber; }
    public String getGuestName() { return guestName; }
    public String getGuestEmail() { return guestEmail; }
    public int getGuests() { return guests; }
    public RoomType getRoomType() { return roomType; }
    public int getNights() { return nights; }
    public Instant getCreatedAt() { return createdAt; }
}
