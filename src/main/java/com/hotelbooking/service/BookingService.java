package com.hotelbooking.service;
import com.hotelbooking.dto.*;
import org.springframework.stereotype.Service;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
@Service
public class BookingService {
    private final Map<String, BookingResponse> bookings = new HashMap<>();
    private final Map<RoomType, Integer> roomCapacity = Map.of(
            RoomType.SINGLE, 10,
            RoomType.DOUBLE, 7,
            RoomType.SUITE, 3
    );
    private final Map<RoomType, Integer> bookedRooms = new HashMap<>();

    public BookingService() {
        bookedRooms.put(RoomType.SINGLE, 0);
        bookedRooms.put(RoomType.DOUBLE, 0);
        bookedRooms.put(RoomType.SUITE, 0);
    }

    public BookingResponse createBooking(BookingRequest request) {
        // --- 1. Kontrollera antal gäster ---
        int maxGuests = switch (request.getRoomType()) {
            case SINGLE -> 1;
            case DOUBLE -> 2;
            case SUITE -> 3;
        };
        if (request.getNumberOfGuests() > maxGuests) {
            throw new IllegalArgumentException("Too many guests for selected room type");
        }// --- 2. Kontrollera datum ---
        LocalDate checkIn = request.getCheckInDate();
        LocalDate checkOut = request.getCheckOutDate();
        if (checkIn == null || checkOut == null || checkIn.isAfter(checkOut) || checkIn.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid check-in/check-out dates");
        }// --- 3. Kontrollera kapacitet ---
        int currentlyBooked = bookedRooms.get(request.getRoomType());
        if (currentlyBooked >= roomCapacity.get(request.getRoomType())) {
            throw new IllegalArgumentException("No available rooms of this type");
        }// --- 4. Beräkna pris ---
        double pricePerNight = switch (request.getRoomType()) {
            case SINGLE -> 1000;
            case DOUBLE -> 1500;
            case SUITE -> 2500;
        };
        int nights = (int) (checkOut.toEpochDay() - checkIn.toEpochDay());
        double totalPrice = pricePerNight * nights;
        PriceInfo priceInfo = new PriceInfo(pricePerNight, nights, totalPrice, "SEK");
// --- 5. Skapa bokning ---
        String bookingNumber = UUID.randomUUID().toString();
        BookingResponse response = new BookingResponse(bookingNumber, priceInfo, BookingStatus.CONFIRMED);
        bookings.put(bookingNumber, response);
        bookedRooms.put(request.getRoomType(), currentlyBooked + 1);
        return response;
    }

    public BookingResponse getBooking(String bookingNumber) {
        BookingResponse response = bookings.get(bookingNumber);
        if (response == null) {
            throw new NoSuchElementException("Booking not found");
        }
        return response;
    }
}