package com.leoita.hotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class Test07MockitoVerifyingBehaviour {

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
    }

    @Test
    void shouldInvokePaymentWhenPrepaid() {

        //given
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2021, 12, 12),
                LocalDate.of(2021, 12, 16),
                2, true);

        //when
        bookingService.makeBooking(bookingRequest);

        // then
        verify(paymentServiceMock, times(1)).pay(bookingRequest, 400.0);
        verifyNoMoreInteractions(paymentServiceMock);
    }

    @Test
    void shouldNotInvokePaymentWhenNotPrepaid() {

        //given
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2021, 12, 12),
                LocalDate.of(2021, 12, 16),
                2, false);

        //when
        bookingService.makeBooking(bookingRequest);

        // then
        verifyNoInteractions(paymentServiceMock);
        verify(paymentServiceMock, never()).pay(any(), anyDouble());
        /*lines 62 and 63 are two ways of doing the same thing*/
    }
}
