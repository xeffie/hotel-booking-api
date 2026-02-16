package com.hotelbooking.dto;

public class BookingRequest {

    private GuestInfo guest;
    private int numberOfGuests;
    private RoomType roomType;
    private int nights;

    public BookingRequest() {}

    public BookingRequest(GuestInfo guest, int numberOfGuests, RoomType roomType, int nights) {
        this.guest = guest;
        this.numberOfGuests = numberOfGuests;
        this.roomType = roomType;
        this.nights = nights;
    }

    public GuestInfo getGuest() {
        return guest;
    }

    public void setGuest(GuestInfo guest) {
        this.guest = guest;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }
}
