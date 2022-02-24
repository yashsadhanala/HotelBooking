package net.java.hotelbooking.springboot.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.hibernate.internal.CoreMessageLogger_.logger;
//import org.hibernate.annotations.common.util.impl.Log_.logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.hotel.Forbooking.service.impl.BookingServiceImpl;

//import lombok.extern.slf4j.Slf4j;
import net.java.hotelbooking.springboot.exception.NoHotelFound;
import net.java.hotelbooking.springboot.exception.NoRoomsAvailable;

//import com.hotel.Forbooking.exception.NoHotelFound;
//import com.hotel.Forbooking.exception.NoRoomsAvailable;

import net.java.hotelbooking.springboot.model.Booking;
import net.java.hotelbooking.springboot.model.Hotel;
import net.java.hotelbooking.springboot.repository.BookingRepository;
import net.java.hotelbooking.springboot.repository.HotelRepository;
import net.java.hotelbooking.springboot.service.BookingService;

//@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

	
    private static final ReentrantLock lock = new ReentrantLock();

	@Autowired	
	public BookingRepository bookingRepository;

	@Autowired
	public HotelRepository hotelRepository;
    Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);


	@Override
	public Booking saveBooking(Booking booking) throws ParseException {
		Booking b = null;
		booking.setCancel(0);
		try {
			Hotel hotel = hotelRepository.findById(booking.getHotelId()).orElseThrow(
					()-> new NoHotelFound());
			Optional<Hotel> h = hotelRepository.findById(booking.getHotelId());
			hotel = h.get();
			List<Booking> bookedRooms =bookingRepository.findAllByHotelIdAndCancelAndCheckin(booking.getHotelId(), 0,booking.getCheckin());
            int r = hotel.getTotalrooms() - bookedRooms.size();
            if (r < 1) {
                throw new NoRoomsAvailable();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(booking.getCheckin()));
            c.add(Calendar.DATE, 1);
            booking.setCheckout(sdf.format(c.getTime()));
        } catch (ParseException e) {
            System.out.println("Enter Dates Correctly\n\n");
            return b;
        } catch (NoRoomsAvailable e) {

            logger.info(e.getMessage() + booking.getHotelId());
            return b;

        } catch (NoHotelFound e) {
            logger.info(e.getMessage() + booking.getHotelId());
            return b;
        }
        Booking booking_dummy = null;
        lock.lock();
        try {
            booking_dummy = bookingRepository.save(booking);
        } finally {
            lock.unlock();
        }

        return booking_dummy;
		
		}
	//	List<Hotel> h =  hotelRepository.findAllByHotelId(booking.getHotelId());
	// hotel = hotelRepository.findById(booking.getHotelId()).orElseThrow()
		//@SuppressWarnings("unused")
	//	Hotel hotel = h.get(0);
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      //  Calendar c = Calendar.getInstance();
     //   c.add(Calendar.DATE, 1);
       // booking.setCheckout(sdf.format(c.getTime()));
//        Booking booking_dummy = null;
//        booking_dummy = bookingRepository.save(booking);
//        return booking_dummy; 
//		}
	

	@Override
	public String cancelBooking(long bookingId) {
      List<Booking> bookingList = bookingRepository.findAllByBookingIdAndCancel(bookingId,0);
      String s;
      lock.lock();
      if (bookingList.size() == 0) {
          s = "Booking Id doesn't exist Id:- ";
      } else {
          bookingList.get(0).setCancel(1);
          bookingRepository.save(bookingList.get(0));
          bookingRepository.deleteAllByBookingId(bookingId);
          s = "Booking Cancelled Id:- ";
      }
      lock.unlock();
      return s;
	
	}

	@Override
	public Booking getBookingById(long bookingId) {
		List<Booking> b =  bookingRepository.findAllByBookingId(bookingId);
		if(b.size()==0) {
			return null;
		}
		return b.get(0);	
	}

	@Override
	public List<Booking> getAllBookings() {
		// TODO Auto-generated method stub
		return bookingRepository.findAll();
	}

}
