package com.leoita.hotel.booking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class Test16MockitoFinalMethods {

    @InjectMocks
    private BookingService bookingService;

    @Mock
    private PaymentService paymentServiceMock;

    @Mock
    private RoomService roomServiceMock;

    @Spy
    private Booking bookingMock;

    @Mock
    private MailSender mailSenderMock;

    @Captor
    private ArgumentCaptor<Double> doubleArgumentCaptor;

    @Test
    void shouldCountAvailablePlacesWhenOneRoomAvailable() {
        when(roomServiceMock.getAvailableRooms())
                .thenReturn(Collections.singletonList(new Room("Room1", 2)));
        //given
        int expected = 2;
        //when
        int actual = bookingService.getAvailablePlaceCount();
        // then
        assertEquals(expected, actual);
    }

}
