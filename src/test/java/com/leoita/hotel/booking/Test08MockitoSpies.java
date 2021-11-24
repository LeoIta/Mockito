package com.leoita.hotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

class Test08MockitoSpies {

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
        this.bookingMock = spy(Booking.class);
        this.bookingService = new BookingService(paymentServiceMock, roomServiceMock,
                bookingMock, mailSenderMock);
    }

    @Test
    void shouldMakeBookingWhenInputOk() {

        //given
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2021, 12, 12),
                LocalDate.of(2021, 12, 16),
                2, true);

        //when
        String bookingId = bookingService.makeBooking(bookingRequest);
        /*when we use mock for bookingMock the Id is the default null, when we use the spy we have an actual ID*/

        // then
        verify(bookingMock).save(bookingRequest);
        System.out.println("bookingId = " + bookingId);

    }

    @Test
    void shouldCancelBookingWhenInputOk() {

        //given
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2021, 12, 12),
                LocalDate.of(2021, 12, 16),
                2, true);
        bookingRequest.setRoomId("1.3");
        String bookingId = "1";

        doReturn(bookingRequest).when(bookingMock).get(bookingId);

        //when
        bookingService.cancelBooking(bookingId);

        // then
        verify(bookingMock).get(bookingId);
        verify(bookingMock).delete(bookingId);
        System.out.println("bookingId = " + bookingId);

    }

}
