package com.leoita.hotel.booking;

public class AvailabilityException extends RuntimeException{
 public AvailabilityException(){
     super("Room not available at the moment");
 }
}
