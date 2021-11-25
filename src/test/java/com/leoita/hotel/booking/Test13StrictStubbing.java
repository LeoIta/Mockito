package com.leoita.hotel.booking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.lenient;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class Test13StrictStubbing {

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
    void shouldInvokePaymentWhenPrepaid() {

        //given
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2021, 12, 12),
                LocalDate.of(2021, 12, 16),
                2, false);
        lenient().when(paymentServiceMock.pay(any(), anyDouble())).thenReturn("1");
/*without Lenient() the line 46 will produce an UnnecessaryStubbingException because we mock an object that will never be used*/
        //when
        bookingService.makeBooking(bookingRequest);

        // then
        then(paymentServiceMock).should(never()).pay(any(),anyDouble());
    }

}
