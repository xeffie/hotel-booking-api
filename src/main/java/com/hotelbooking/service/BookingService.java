package com.hotelbooking.service;

import com.hotelbooking.dto.BookingRequest;
import com.hotelbooking.dto.BookingResponse;

public interface BookingService {

    // DETTA ÄR ENDAST EN DUMMY FÖR ATT TESTA POSTA EN BOOKING PÅ /bookings !!!
    // @Hanad ersätter med korrekt kod

    BookingResponse createBooking(BookingRequest request);
    BookingResponse getBooking(String bookingNumber);
}