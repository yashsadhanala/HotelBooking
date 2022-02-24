package net.java.springboot.ServiceImplTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
//import org.junit.Test;
//import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.ContextConfiguration;

import net.java.hotelbooking.springboot.model.Booking;
import net.java.hotelbooking.springboot.model.Hotel;
import net.java.hotelbooking.springboot.repository.BookingRepository;
import net.java.hotelbooking.springboot.repository.HotelRepository;
import net.java.hotelbooking.springboot.service.BookingService;
import net.java.hotelbooking.springboot.service.HotelService;
import net.java.hotelbooking.springboot.service.impl.BookingServiceImpl;

//@RunWith(MockitoJUnitRunner.class)
//@ContextConfiguration("/applicationContext.xml")

@SpringBootTest(classes = {BookingServiceImplTest.class})
public class BookingServiceImplTest {
	
	@InjectMocks
	private BookingServiceImpl bookingServiceImpl;
	
	//@InjectMocks
	@Mock
	private BookingService bookingservice;
	
	@Mock
	private BookingRepository bookingRepository;
	
	@Mock
	private HotelRepository hotelRepository;
//	
//	@Test
//	public void savebooking() throws ParseException {
////////		Hotel hotel = new Hotel();
////////		hotel.setHotelId(2);
////////		hotel.setCity("Delhi");
////////		hotel.setCityCode(60);
////////		hotel.setHotelname("Asia");
////////		hotel.setRoomsavailable(10);
////////		hotel.setTotalrooms(10);
//		Booking booking = new Booking();
//		booking.setBookingId(4);
//	    booking.setHotelId(3);
//		booking.setCheckin("2022-02-24");
//		booking.setCheckout("2022-02-26");
//		booking.setCancel(0);
//		Mockito.when(bookingRepository.save(booking)).thenReturn(booking);
//////		//assertThat(bookingServiceImpl.saveBooking(booking)).isEqualTo(booking
//	assertThat(bookingServiceImpl.saveBooking(booking)).isEqualTo(booking);
//////	//	assertEquals(booking,bookingServiceImpl.saveBooking(booking));
//	}
	@Order(1)
	@Test
	public void getAllBookings() {
		Booking booking1 = new Booking();
		booking1.setBookingId(3);
		booking1.setHotelId(3);
		booking1.setCheckin("2022/02/24");
		booking1.setCheckout("2022/02/26");
		booking1.setCancel(0);
		
		Booking booking2 = new Booking();
		booking2.setBookingId(4);
		booking2.setHotelId(4);
		booking2.setCheckin("2022/02/25");
		booking2.setCheckout("2022/02/26");
		booking2.setCancel(0);
		
		List <Booking>	bookingList = new ArrayList<>();
		
		bookingList.add(booking1);
		bookingList.add(booking2);
		
		Mockito.when(bookingRepository.findAll()).thenReturn(bookingList);
		assertThat(bookingServiceImpl.getAllBookings()).isEqualTo(bookingList);
		
	}
	
//	@Test @Order(2)
//	public void getBookingById() {
//		Booking booking1 = new Booking();
//		booking1.setBookingId(3);
//		booking1.setHotelId(3);
//		booking1.setCheckin("2022/02/24");
//		booking1.setCheckout("2022/02/25");
//		booking1.setCancel(0);
//		
//		Booking booking2 = new Booking();
//		booking2.setBookingId(4);
//		booking2.setHotelId(4);
//		booking2.setCheckin("2022/02/22");
//		booking2.setCheckout("2022/02/26");
//		booking2.setCancel(0);
//		
//		List <Booking>	bookingList = new ArrayList<>();
//		
//		bookingList.add(booking1);
//		bookingList.add(booking2);
//		
//		int bookingId = 1;
//		
//		Mockito.when(bookingRepository.findAll()).thenReturn(bookingList);
//		assertEquals(bookingServiceImpl.getBookingById(bookingId).getBookingId(),bookingId);
//	}
//	
	
	

}
//package com.infotech.book.ticket.app.service;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertFalse;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.infotech.book.ticket.app.dao.TicketBookingDao;
//import com.infotech.book.ticket.app.entities.Ticket;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class TicketBookingServiceTest {
//
//	@Autowired
//	private TicketBookingService ticketBookingService;
//	
//	@MockBean
//	private TicketBookingDao ticketBookingDao;
//	
//	@Test
//	//@Ignore
//	public void testCreateTicket(){
//
//		Ticket ticket = new Ticket();
//		ticket.setTicketId(1);
//		ticket.setPassengerName("Martin Bingel");
//		ticket.setSourceStation("Kolkata");
//		ticket.setDestStation("Delhi");
//		ticket.setBookingDate(new Date());
//		ticket.setEmail("martin.s2017@gmail.com");
//		
//	    Mockito.when(ticketBookingDao.save(ticket)).thenReturn(ticket);
//	    
//	    assertThat(ticketBookingService.createTicket(ticket)).isEqualTo(ticket);
//	
//	}
//	
//	
//	@Test
//	public void testGetTicketById(){
//		Ticket ticket = new Ticket();
//		ticket.setTicketId(1);
//		ticket.setPassengerName("Martin Bingel");
//		ticket.setSourceStation("Kolkata");
//		ticket.setDestStation("Delhi");
//		ticket.setBookingDate(new Date());
//		ticket.setEmail("martin.s2017@gmail.com");
//		
//	    Mockito.when(ticketBookingDao.findOne(1)).thenReturn(ticket);
//	    assertThat(ticketBookingService.getTicketById(1)).isEqualTo(ticket);
//	}
//	
//	@Test
//	public void testGetAllBookedTickets(){
//		Ticket ticket1 = new Ticket();
//		ticket1.setPassengerName("Martin Bingel");
//		ticket1.setSourceStation("Kolkata");
//		ticket1.setDestStation("Delhi");
//		ticket1.setBookingDate(new Date());
//		ticket1.setEmail("martin.s2017@gmail.com");
//		
//		Ticket ticket2 = new Ticket();
//		ticket2.setPassengerName("Sean Murphy");
//		ticket2.setSourceStation("Kolkata");
//		ticket2.setDestStation("Mumbai");
//		ticket2.setBookingDate(new Date());
//		ticket2.setEmail("sean.s2017@gmail.com");
//		
//		List<Ticket> ticketList = new ArrayList<>();
//		ticketList.add(ticket1);
//		ticketList.add(ticket2);
//		
//		Mockito.when(ticketBookingDao.findAll()).thenReturn(ticketList);
//		
//		assertThat(ticketBookingService.getAllBookedTickets()).isEqualTo(ticketList);
//	}
//	
//	
//	@Test
//	public void testDeleteTicket(){
//		Ticket ticket = new Ticket();
//		ticket.setTicketId(1);
//		ticket.setPassengerName("Martin Bingel");
//		ticket.setSourceStation("Kolkata");
//		ticket.setDestStation("Delhi");
//		ticket.setBookingDate(new Date());
//		ticket.setEmail("martin.s2017@gmail.com");
//		
//	    Mockito.when(ticketBookingDao.findOne(1)).thenReturn(ticket);
//	    Mockito.when(ticketBookingDao.exists(ticket.getTicketId())).thenReturn(false);
//	   assertFalse(ticketBookingDao.exists(ticket.getTicketId()));
//	}
//	
//	
//	@Test
//	public void testUpdateTicket(){
//		Ticket ticket = new Ticket();
//		ticket.setTicketId(1);
//		ticket.setPassengerName("Martin Bingel");
//		ticket.setSourceStation("Kolkata");
//		ticket.setDestStation("Delhi");
//		ticket.setBookingDate(new Date());
//		ticket.setEmail("martin.s2017@gmail.com");
//		
//		Mockito.when(ticketBookingDao.findOne(1)).thenReturn(ticket);
//		
//		ticket.setEmail("martin.s2000@gmail.com");
//		Mockito.when(ticketBookingDao.save(ticket)).thenReturn(ticket);
//		
//		assertThat(ticketBookingService.updateTicket(1, "martin.s2017@gmail.com")).isEqualTo(ticket);
//		
//	}
//	
//	@Test
//	public void testGetTicketByEmail(){
//		
//		Ticket ticket = new Ticket();
//		ticket.setTicketId(1);
//		ticket.setPassengerName("Martin Bingel");
//		ticket.setSourceStation("Kolkata");
//		ticket.setDestStation("Delhi");
//		ticket.setBookingDate(new Date());
//		ticket.setEmail("martin.s2017@gmail.com");
//		
//	    Mockito.when(ticketBookingDao.findByEmail("martin.s2017@gmail.com")).thenReturn(ticket);
//	    
//	    assertThat(ticketBookingService.getTicketByEmail("martin.s2017@gmail.com")).isEqualTo(ticket);
//	}
//	
//}