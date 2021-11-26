package com.leoita.hotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


class Test09MockitoVoidMethods {

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
    void shouldThrowExceptionWhenMailNotReady() {

        //given
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2021, 12, 12),
                LocalDate.of(2021, 12, 16),
                2, false);

        doThrow(AvailabilityException.class).when(mailSenderMock).sendBookingConfirmation(any());
        //when
        Executable executable = () -> bookingService.makeBooking(bookingRequest);

        // then
        assertThrows(AvailabilityException.class, executable);
    }

    @Test
    void shouldNotThrowExceptionWhenMailNotReady() {

        //given
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2021, 12, 12),
                LocalDate.of(2021, 12, 16),
                2, false);

        doNothing().when(mailSenderMock).sendBookingConfirmation(any());
        //when
        String id = bookingService.makeBooking(bookingRequest);

        // then
        //do not throw exceptions
        verify(mailSenderMock, times(1)).sendBookingConfirmation(any());
        assertNull(id);
    }
}
