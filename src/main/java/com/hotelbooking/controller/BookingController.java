package com.hotelbooking.controller;

import com.hotelbooking.dto.BookingRequest;
import com.hotelbooking.dto.BookingResponse;
import com.hotelbooking.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest request) {
        BookingResponse response = bookingService.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{bookingNumber}")
    public ResponseEntity<BookingResponse> getBooking(@PathVariable String bookingNumber) {
        BookingResponse response = bookingService.getBooking(bookingNumber);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        return  ResponseEntity.ok(bookingService.getAllBookings());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{bookingNumber}")
    public ResponseEntity<Void> deleteBooking(@PathVariable String bookingNumber) {
        bookingService.deleteBooking(bookingNumber);
        return ResponseEntity.noContent().build();
    }
}
