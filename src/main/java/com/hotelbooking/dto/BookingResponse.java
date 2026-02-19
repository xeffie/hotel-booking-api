package com.hotelbooking.dto;

import com.hotelbooking.model.BookingStatus;

import java.time.Instant;

public class BookingResponse {

    private String bookingNumber;
    private PriceInfo price;
    private BookingStatus status;
    private String guestName;
    private String guestEmail;
    private int guests;
    private String roomType;
    private int nights;
    private String ownerUsername;
    private Instant createdAt;

    public BookingResponse() {}

    public BookingResponse(String bookingNumber, PriceInfo price, BookingStatus status, String guestName, String guestEmail, int guests, String roomType, int nights, String ownerUsername, Instant createdAt) {
        this.bookingNumber = bookingNumber;
        this.price = price;
        this.status = status;
        this.guestName = guestName;
        this.guestEmail = guestEmail;
        this.guests = guests;
        this.roomType = roomType;
        this.nights = nights;
        this.ownerUsername = ownerUsername;
        this.createdAt = createdAt;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public PriceInfo getPrice() {
        return price;
    }

    public void setPrice(PriceInfo price) {
        this.price = price;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
