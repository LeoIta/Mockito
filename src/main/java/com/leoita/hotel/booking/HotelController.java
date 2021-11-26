package com.leoita.hotel.booking;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HotelController {

    private BookingService bookingService;

    @RequestMapping("/greeting")
    public String index() {
        return "Greetings from The Happy Hotel. We've got enough beds for " + bookingService.getAvailablePlaceCount() + " guests!";
    }

}
