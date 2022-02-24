package net.java.hotelbooking.springboot.service;


import java.text.ParseException;
import java.util.List;

import net.java.hotelbooking.springboot.model.Booking;
//import net.java.hotelbooking.springboot.model.Hotel;


public interface BookingService {
	Booking saveBooking(Booking booking) throws ParseException;
    String cancelBooking(long bookingId);
	Booking getBookingById(long bookingId);
	List <Booking> getAllBookings();
//	List<Booking> getAllBookings();

}
