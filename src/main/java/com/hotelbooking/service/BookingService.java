package com.hotelbooking.service;

import com.hotelbooking.dto.*;
import com.hotelbooking.exception.BookingNotFoundException;
import com.hotelbooking.exception.InvalidBookingException;
import com.hotelbooking.exception.OutOfRoomsException;
import com.hotelbooking.model.BookingStatus;
import com.hotelbooking.model.RoomType;
import com.hotelbooking.model.Booking;
import com.hotelbooking.repository.BookingRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    private final Map<RoomType, Integer> roomCapacity = Map.of(
            RoomType.SINGLE, 10,
            RoomType.DOUBLE, 7,
            RoomType.SUITE, 3
    );

    private final Map<RoomType, Integer> bookedRooms = new HashMap<>();

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;

        bookedRooms.put(RoomType.SINGLE, 0);
        bookedRooms.put(RoomType.DOUBLE, 0);
        bookedRooms.put(RoomType.SUITE, 0);
    }

    public BookingResponse createBooking(BookingRequest request) {

        // 1️⃣ Kontrollera antal gäster
        if (request.getNumberOfGuests() > request.getRoomType().getMaxGuests()) {
            throw new InvalidBookingException("Too many guests for selected room type");
        }

        // 2️⃣ Kontrollera datum
        LocalDate checkIn = request.getCheckInDate();
        LocalDate checkOut = request.getCheckOutDate();

        if (checkIn == null ||
                checkOut == null ||
                !checkOut.isAfter(checkIn) ||
                checkIn.isBefore(LocalDate.now())) {

            throw new InvalidBookingException("Invalid check-in/check-out dates");
        }

        // 3️⃣ Kontrollera kapacitet
        int currentlyBooked = bookedRooms.get(request.getRoomType());

        if (currentlyBooked >= roomCapacity.get(request.getRoomType())) {
            throw new OutOfRoomsException("No available rooms of this type");
        }

        // 4️⃣ Beräkna pris
        int nights = (int) ChronoUnit.DAYS.between(checkIn, checkOut);

        PriceInfo priceInfo = buildPriceInfo(request.getRoomType(), nights);

        // 5️⃣ Skapa bokning
        String ownerUsername = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        Booking b = new Booking(
                ownerUsername,
                request.getGuest().getName(),
                request.getGuest().getEmail(),
                request.getNumberOfGuests(),
                request.getRoomType(),
                nights
        );

        bookingRepository.save(b);

        BookingResponse response = new BookingResponse(
                b.getBookingNumber(),
                priceInfo,
                BookingStatus.CONFIRMED
        );

        bookedRooms.put(request.getRoomType(), currentlyBooked + 1);

        return response;
    }

    public BookingResponse getBooking(String bookingNumber) {
        Booking b = bookingRepository
                .findByBookingNumber(bookingNumber)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found"));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName();

        boolean isAdmin = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(a -> a.equals("ROLE_ADMIN"));

        if (!isAdmin && !b.getOwnerUsername().equals(currentUser)) {
            throw new AccessDeniedException("You do not have permission to access this booking");
        }

        PriceInfo priceInfo = buildPriceInfo(b.getRoomType(), b.getNights());

        return new BookingResponse(
                b.getBookingNumber(),
                priceInfo,
                BookingStatus.CONFIRMED
        );
    }

    public List<BookingResponse> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(b -> {
                    PriceInfo priceInfo = buildPriceInfo(b.getRoomType(), b.getNights());
                    return new BookingResponse(
                            b.getBookingNumber(),
                            priceInfo,
                            BookingStatus.CONFIRMED
                    );
                })
                .toList();
    }

    public void deleteBooking(String bookingNumber) {
        Booking b = bookingRepository.findByBookingNumber(bookingNumber)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found"));

        bookingRepository.deleteByBookingNumber(bookingNumber);

        int currentlyBooked = bookedRooms.get(b.getRoomType());
        bookedRooms.put(b.getRoomType(), Math.max(0, currentlyBooked - 1));
    }

    private double pricePerNight(RoomType roomType) {
        return switch (roomType) {
            case SINGLE -> 1000;
            case DOUBLE -> 1500;
            case SUITE -> 2500;
        };
    }

    private PriceInfo buildPriceInfo(RoomType roomType, int nights) {
        double perNight = pricePerNight(roomType);
        double total = perNight * nights;
        return new PriceInfo(perNight, nights, total, "SEK");
    }
}
