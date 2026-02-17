package com.hotelbooking.dto;

import com.hotelbooking.model.BookingStatus;

public class BookingResponse {

    private String bookingNumber;
    private PriceInfo price;
    private BookingStatus status;

    public BookingResponse() {}

    public BookingResponse(String bookingNumber, PriceInfo price, BookingStatus status) {
        this.bookingNumber = bookingNumber;
        this.price = price;
        this.status = status;
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
}
