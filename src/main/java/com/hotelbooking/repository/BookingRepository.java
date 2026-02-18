package com.hotelbooking.repository;

import com.hotelbooking.model.Booking;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookingRepository {

    private final Map<String, Booking> bookings = new HashMap<>();

    public Booking save(Booking booking) {
        bookings.put(booking.getBookingNumber(), booking);
        return booking;
    }

    public Optional<Booking> findByBookingNumber(String bookingNumber) {
        return Optional.ofNullable(bookings.get(bookingNumber));
    }

    public List<Booking> findAll() {
        return new ArrayList<>(bookings.values());
    }

    public void deleteByBookingNumber(String bookingNumber) {
        bookings.remove(bookingNumber);
    }
}
