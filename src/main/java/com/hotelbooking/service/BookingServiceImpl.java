package com.hotelbooking.service;

import com.hotelbooking.dto.BookingRequest;
import com.hotelbooking.dto.BookingResponse;
import com.hotelbooking.dto.BookingStatus;
import com.hotelbooking.dto.PriceInfo;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    // DETTA ÄR ENDAST EN HÅRDKODAD DUMMY FÖR ATT TESTA POSTA EN BOOKING PÅ /bookings !!!
    // @Hanad ersätter med korrekt kod

    public BookingResponse createBooking(BookingRequest request) {
        PriceInfo price = new PriceInfo(1000.0, request.getNights(), 1000.0 * request.getNights(), "SEK");
        return new BookingResponse("123", price, BookingStatus.CONFIRMED);
    }

    public BookingResponse getBooking(String bookingNumber) {
        PriceInfo price = new PriceInfo(1000.0, 1, 1000.0, "SEK");
        return new BookingResponse(bookingNumber, price, BookingStatus.CONFIRMED);
    }
}
