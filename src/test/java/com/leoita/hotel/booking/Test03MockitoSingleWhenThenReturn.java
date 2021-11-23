package com.leoita.hotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Test03MockitoSingleWhenThenReturn {

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
    void shouldGetAvailableRoomWhenOneRoomAvailable() {
        when(roomServiceMock.getAvailableRooms())
                .thenReturn(Collections.singletonList(new Room("Room1", 2)));
        //given
        int expected = 2;
        //when
        int actual = bookingService.getAvailablePlaceCount();
        // then
        assertEquals(expected, actual);
    }

    @Test
    void shouldGetAvailableRoomWhenMultipleRoomsAvailable() {
        when(roomServiceMock.getAvailableRooms())
                .thenReturn(Arrays.asList(
                        new Room("Room1", 2),
                        new Room("Room2", 5),
                        new Room("Room3", 3))
                );
        //given
        int expected = 10;
        //when
        int actual = bookingService.getAvailablePlaceCount();
        // then
        assertEquals(expected, actual);
    }


}
