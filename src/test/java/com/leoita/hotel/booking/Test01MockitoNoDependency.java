package com.leoita.hotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;


class Test01MockitoNoDependency {

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
    void shouldCalculateCorrectPriceWhenCorrectInput() {

        //given
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2021, 12, 12),
                LocalDate.of(2021, 12, 16),
                2, false);
        double expected = 4 * 2 * 50.0;
        //when
        double actual = bookingService.calculatePrice(bookingRequest);
        // then
        assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateCorrectPriceInEuroWhenCorrectInput() {

        //given
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2021, 12, 12),
                LocalDate.of(2021, 12, 16),
                2, false);
        double expected = 4 * 2 * 50.0 * 0.85;
        //when
        double actual = bookingService.calculatePriceEuro(bookingRequest);
        // then
        assertEquals(expected, actual);
    }

}
