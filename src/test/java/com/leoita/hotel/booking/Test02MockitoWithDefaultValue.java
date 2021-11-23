package com.leoita.hotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;


class Test02MockitoWithDefaultValue {

    private BookingService bookingService;
    private PaymentService paymentServiceMock;
    private RoomService roomServiceMock;
    private Booking bookingMock;
    private MailSender mailSenderMock;


    @BeforeEach
    void setup() {
        this.paymentServiceMock = mock(PaymentService.class);
        this.roomServiceMock = mock(RoomService.class);
        this.mailSenderMock = mock(MailSender.class);
        this.bookingMock = mock(Booking.class);
        this.bookingService = new BookingService(paymentServiceMock, roomServiceMock,
                bookingMock, mailSenderMock);
        System.out.println("list return " + roomServiceMock.getAvailableRooms());
        System.out.println("Object return " + roomServiceMock.findAvailableRoomId(null));
        System.out.println("Primitive returned " + roomServiceMock.getRoomCount());
    }

    @Test
    void shouldGetAvailablePlacesCount() {

        //given
        int expected = 0 ;
        //when
        int actual= bookingService.getAvailablePlaceCount();
        // then
        assertEquals(expected,actual);
    }


}
