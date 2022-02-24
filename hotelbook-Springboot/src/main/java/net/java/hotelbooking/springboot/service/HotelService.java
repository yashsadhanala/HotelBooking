package net.java.hotelbooking.springboot.service;
import java.util.Date;
//import java.text.ParseException;
import java.util.List;

import net.java.hotelbooking.springboot.model.Hotel;
public interface HotelService {
	Hotel saveHotel(Hotel hotel);
	List <Hotel>getAllHotels();
	Hotel getHotelById(long hotelId);
	Hotel updateHotel(Hotel hotel,long hotelId);
	void deleteHotel(long hotelId);
	List<Hotel> getHotelListById(int cityCode);
    List<Hotel> getHotelList(long hotelId, Date checkIn, Date checkOut);

}
