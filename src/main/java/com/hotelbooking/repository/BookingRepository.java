package com.hotelbooking.repository;

import com.hotelbooking.dto.BookingResponse;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class BookingRepository {

    private final Map<String, BookingResponse> bookings = new HashMap<>();

    public BookingResponse save(String bookingNumber, BookingResponse booking) {
        bookings.put(bookingNumber, booking);
        return booking;
    }

    public Optional<BookingResponse> findByBookingNumber(String bookingNumber) {
        return Optional.ofNullable(bookings.get(bookingNumber));
    }
}
