package com.leoita.hotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class Test06MockitoMatchers {

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
    void shouldNotCompleteBookingWhenPriceTooHigh() {

        //given
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2021, 12, 12),
                LocalDate.of(2021, 12, 16),
                2, true);
        /*we use any() for objects but for primitive we use anyInt(), anyDouble() etc.*/
        /*any() match also the null value, anyString() not*/
        when(this.paymentServiceMock.pay(any(), anyDouble()))
                .thenThrow(AvailabilityException.class);

        //when
        Executable executable = () -> bookingService.makeBooking(bookingRequest);

        // then
        assertThrows(AvailabilityException.class, executable);
    }

    @Test
    void shouldNotCompleteBookingWhenPriceTooHighV2() {

        //given
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2021, 12, 12),
                LocalDate.of(2021, 12, 16),
                2, true);
        /*if we want to mix actual value with matchers we need to use the eq(value)*/
        when(this.paymentServiceMock.pay(any(), eq(400.0)))
                .thenThrow(AvailabilityException.class);

        //when
        Executable executable = () -> bookingService.makeBooking(bookingRequest);

        // then
        assertThrows(AvailabilityException.class, executable);
    }

}
