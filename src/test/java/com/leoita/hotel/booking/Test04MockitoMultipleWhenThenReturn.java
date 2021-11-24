package com.leoita.hotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Test04MockitoMultipleWhenThenReturn {

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
    void shouldCountAvailablePlacesWhenCalledMultipleTimes() {
        when(roomServiceMock.getAvailableRooms())
                .thenReturn(Collections.singletonList(new Room("Room1", 2)))
                .thenReturn(Collections.emptyList());
        //given
        int expectedFirstCall = 2;
        int expectedSecondCall = 0;
        //when
        int actualFirstCall = bookingService.getAvailablePlaceCount();
        int actualSecondCall = bookingService.getAvailablePlaceCount();
        // then
        assertAll(
                () -> assertEquals(expectedFirstCall,actualFirstCall),
                () -> assertEquals(expectedSecondCall,actualSecondCall));

    }
}
