package net.java.hotelbooking.springboot.service.impl;

import java.text.SimpleDateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
//import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import lombok.extern.slf4j.Slf4j;

//import com.hotel.Forbooking.service.impl.BookingServiceImpl;

import net.java.hotelbooking.springboot.repository.BookingRepository;
import net.java.hotelbooking.springboot.repository.HotelRepository;
import net.java.hotelbooking.springboot.exception.ResourceNotFoundException;
import net.java.hotelbooking.springboot.model.Booking;
import net.java.hotelbooking.springboot.model.Hotel;
import net.java.hotelbooking.springboot.service.HotelService;


//@Slf4j
@Service
public class HotelServiceImpl implements HotelService {
@Autowired
public BookingRepository bookingRepository;
Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

public HotelRepository hotelRepository;
	
	public HotelServiceImpl(HotelRepository hotelRepository) {
		super();
		this.hotelRepository = hotelRepository;
	}

	@Override
	public Hotel saveHotel(Hotel hotel) {
	     hotel.setRoomsavailable(hotel.getTotalrooms()); 
	     return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotels() {
		return hotelRepository.findAll();

	}

	@Override
	public Hotel getHotelById(long hotelId) {
		List<Hotel> h =  hotelRepository.findAllByHotelId(hotelId);
		if(h.size()==0) {
			return null;
		}
		return h.get(0);
	}

	@Override
	public Hotel updateHotel(Hotel hotel, long hotelId) {
		Hotel existingHotel = hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel","HotelId",hotelId));
		existingHotel.setHotelname(hotel.getHotelname());
		existingHotel.setCity(hotel.getCity());
		existingHotel.setTotalrooms(hotel.getTotalrooms());
		existingHotel.setRoomsavailable(hotel.getTotalrooms());
		existingHotel.setCityCode(hotel.getCityCode());
        logger.info("Hotel Updated " + hotelId);
		hotelRepository.save(existingHotel);
		return existingHotel;
	}

	@Override
	public void deleteHotel(long hotelId) {
		hotelRepository.findById(hotelId).orElseThrow(()->
		new ResourceNotFoundException("Hotel","HotelId",hotelId));
        hotelRepository.deleteById(hotelId);
	}

	@Override
	public List<Hotel> getHotelListById(int cityCode) {
        return hotelRepository.findByCityCode(cityCode);
		//return null;
	}

	@Override
	public List<Hotel> getHotelList(long hotelId, Date checkIn, Date checkOut) {
        List<Hotel> hotel = hotelRepository.findAllByHotelId(hotelId);//city
        List<Hotel> hotel_available=new ArrayList<>();
        for (int i = 0; i < hotel.size(); i++) {
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            formatter.format(checkIn);
            List<Booking> bookedRooms = bookingRepository.findAllByBookingIdAndCancel(hotel.get(i).getHotelId(),0);
            int r = hotel.get(i).getTotalrooms() - bookedRooms.size();
            hotel.get(i).setRoomsavailable(r);
        }
        for(int i=0;i<hotel.size();i++)
        {
            if(hotel.get(i).getRoomsavailable()>0)
            {
                hotel_available.add(hotel.get(i));
            }
        }
        
        if (hotel_available.size() == 0) {
            logger.info("There are no hotels available in " + hotelId);
        } else {
            logger.info("List of all hotels available in " + hotelId + " is send to the client");
        }
        return hotel_available;
	}
}
