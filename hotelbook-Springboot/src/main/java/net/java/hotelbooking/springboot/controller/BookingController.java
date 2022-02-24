package net.java.hotelbooking.springboot.controller;

import java.text.DateFormat;
import java.text.ParseException;
//import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.java.hotelbooking.springboot.exception.InvalidDateFormat;
import net.java.hotelbooking.springboot.exception.NoHotelFound;

//import com.hotel.Forbooking.exception.InvalidDateFormat;
//import com.hotel.Forbooking.exception.NoHotelFound;

//import lombok.extern.slf4j.Slf4j;
import net.java.hotelbooking.springboot.model.Booking;
//import net.java.hotelbooking.springboot.model.Hotel;
import net.java.hotelbooking.springboot.service.BookingService;
import net.java.hotelbooking.springboot.service.impl.BookingServiceImpl;

@Slf4j
@RestController
@RequestMapping("/api/booking")
public class BookingController {
  
	//private Object log;
    private BookingService bookingService;
    Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

    
	public BookingController(BookingService bookingService) {
		super();
		this.bookingService = bookingService;
	}


	@PostMapping("/a")
    public ResponseEntity<Booking> saveBooking1(@RequestParam(name = "HotelId") int hotelId,
                                                @RequestParam(name = "CheckIn") Date checkin) throws ParseException{
        System.out.println("Running........");
        Booking booking = new Booking();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(checkin);
        booking.setHotelId(hotelId);
        booking.setCheckin(strDate);
    //    return  new ResponseEntity<Booking>(bookingService.saveBooking(booking), HttpStatus.CREATED);
     	log.info("New booking request received on " + booking.getCheckin() + " for hotel Id " + booking.getHotelId());
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(booking.getCheckin());
            Date date1 = new Date();
            if (date1.after(date)) {
                throw new InvalidDateFormat("Please Enter date after " + date);
            }

        } catch (Exception e) {
            throw new InvalidDateFormat();
        }

        ResponseEntity<Booking> res = new ResponseEntity<Booking>(bookingService.saveBooking(booking), HttpStatus.CREATED);
        if (res.getBody() == null)
            throw new NoHotelFound();
        return res;
    }								
	
	
    @PostMapping
    public ResponseEntity<Booking> saveBooking(@RequestBody Booking booking) throws ParseException{
    	//return new ResponseEntity<Booking>(bookingService.saveBooking(booking), HttpStatus.CREATED);
       boolean b = true;
       String s = "";
       s = booking.getCheckout();
       if(booking.getCheckout() == null) {
    	   b = false;
       }
       booking.setCheckout(null);
           log.info("New booking request received on " + booking.getCheckin() + " for hotel Id " + booking.getHotelId());

       try {
           Date date = new SimpleDateFormat("yyyy-MM-dd").parse(booking.getCheckin());
           Date date1 = new Date();
           if (date1.after(date)) {
               throw new InvalidDateFormat("Please Enter date after " + date);
           }

       } catch (Exception e) {
           throw new InvalidDateFormat();
       }

       ResponseEntity<Booking> res = new ResponseEntity<Booking>(bookingService.saveBooking(booking), HttpStatus.CREATED);
       if (b) {
           res.getBody().setCheckout(s);
       }

       if (res.getBody() == null)
           throw new NoHotelFound();
       return res;
    }
    
    
    @GetMapping()
	public List<Booking> getAllBookings(){
		return bookingService.getAllBookings();	}

    
    @GetMapping("{bookingId}")
	public ResponseEntity<Booking> getBookingById(@PathVariable("bookingId") long bookingId){
		return new ResponseEntity<Booking>(bookingService.getBookingById(bookingId),HttpStatus.OK);
	}

    
    @DeleteMapping("{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable("bookingId") long  bookingId) {
        log.info("Booking cancellation request received  with booking Id:- " + bookingId);
    	String s = bookingService.cancelBooking(bookingId);
        s = s + bookingId;
        log.info(s);
        return new ResponseEntity<String>(s, HttpStatus.OK);
    }
 
    
    @PutMapping("{bookingId}")
    public ResponseEntity<String> updateBooking(@PathVariable("bookingId") long bookingId) {
        String s = bookingService.cancelBooking(bookingId);
        return new ResponseEntity<String>(s, HttpStatus.OK);
    }
    
    
    @ExceptionHandler({NoHotelFound.class})
    public String exceptionHandler() {
        return "No Hotel Found";
    }

    
    @ExceptionHandler({InvalidDateFormat.class})
    public String exceptionDate() {
        return "Enter valid date";
    }

    
}

