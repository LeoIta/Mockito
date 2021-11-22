package com.leoita.hotel.booking;

import lombok.AllArgsConstructor;

import java.time.temporal.ChronoUnit;

@AllArgsConstructor
public class BookingService {

    private static final double BASE_PRICE_USD = 50.0;
    private final PaymentService paymentService;    //take care of the money
    private final RoomService roomService;          //manage the rooms availability
    private final Booking booking;                  //take keeps list of all booking
    private final MailSender mailSender;            //take care of mail system

    //needs RoomService
    public int getAvailablePlaceCount() {
        return roomService.getAvailableRooms()
                .stream()
                .map(Room::getCapacity)
                .reduce(0, Integer::sum);
    }

    //no dependency
    public double calculatePrice(BookingRequest bookingRequest) {
        long nights = ChronoUnit.DAYS.between(bookingRequest.getDateFrom(), bookingRequest.getDateTo());
        return BASE_PRICE_USD * bookingRequest.getGuestCount() * nights;
    }

    //no dependency
    public double calculatePriceEuro(BookingRequest bookingRequest) {
        return CurrencyConverter.toEuro(calculatePrice(bookingRequest));
    }

    //needs RoomService, PaymentService, MailSender and Booking
    public String makeBooking(BookingRequest bookingRequest) {
        String roomId = roomService.findAvailableRoomId(bookingRequest);
        double price = calculatePrice(bookingRequest);

        if (bookingRequest.isPrepaid()) {
            paymentService.pay(bookingRequest, price);
        }

        bookingRequest.setRoomId(roomId);
        String bookingId = Booking.save(bookingRequest);
        roomService.bookRoom(roomId);
        mailSender.sendBookingConfirmation(bookingId);
        return bookingId;
    }

    //needs RoomService and Booking
    public void cancelBooking(String id) {
        BookingRequest request = Booking.get(id);
        roomService.unbookRoom(request.getRoomId());
        Booking.delete(id);
    }
}
