package com.leoita.hotel.booking;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Booking {

    private static final Map<String, BookingRequest> bookings = new HashMap<>();

    public static String save(BookingRequest bookingRequest) {
        String id = UUID.randomUUID().toString();
        bookings.put(id, bookingRequest);
        return id;
    }

    public static BookingRequest get(String id) {
        return bookings.get(id);
    }

    public static void delete(String bookingId) {
        bookings.remove(bookingId);
    }
}
