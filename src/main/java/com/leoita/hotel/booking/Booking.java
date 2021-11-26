package com.leoita.hotel.booking;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class Booking {

    private static final Map<String, BookingRequest> bookings = new HashMap<>();

    public String save(BookingRequest bookingRequest) {
        String id = UUID.randomUUID().toString();
        bookings.put(id, bookingRequest);
        return id;
    }

    public BookingRequest get(String id) {
        return bookings.get(id);
    }

    public void delete(String bookingId) {
        bookings.remove(bookingId);
    }
}
