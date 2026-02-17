package com.hotelbooking.dto;

import com.hotelbooking.model.RoomType;

import java.time.LocalDate;

public class BookingRequest {

    private GuestInfo guest;
    private int numberOfGuests;
    private RoomType roomType;
    private int nights;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public BookingRequest() {}

    public BookingRequest(GuestInfo guest, int numberOfGuests, RoomType roomType,
                          LocalDate checkInDate, LocalDate checkOutDate) {
        this.guest = guest;
        this.numberOfGuests = numberOfGuests;
        this.roomType = roomType;
        //this.nights = nights;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
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

    //public int getNights() {return nights;}

  //  public void setNights(int nights) {this.nights = nights;}

    public LocalDate getCheckInDate() {
        return checkInDate;
    }public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }public LocalDate getCheckOutDate() {
        return checkOutDate;
    }public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
