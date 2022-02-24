package net.java.springboot.ServiceImplTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import net.java.hotelbooking.springboot.model.Hotel;
import net.java.hotelbooking.springboot.repository.HotelRepository;
import net.java.hotelbooking.springboot.service.BookingService;
import net.java.hotelbooking.springboot.service.impl.HotelServiceImpl;




@SpringBootTest(classes = {HotelServiceImplTest.class})
public class HotelServiceImplTest {

	@Mock
	private HotelRepository hotelRepository;
	

	@InjectMocks
	private HotelServiceImpl hotelServiceImpl;
	
	@Mock
	private BookingService bookingService;
	
	
	@Test
	public void saveHotel() {
		Hotel hotel = new Hotel();
		hotel.setHotelId(1);
		hotel.setCity("Delhi");
		hotel.setCityCode(60);
		hotel.setHotelname("Dharma");
		hotel.setTotalrooms(5);
		hotel.setRoomsavailable(5);
		
		Mockito.when(hotelRepository.save(hotel)).thenReturn(hotel);
		assertThat(hotelServiceImpl.saveHotel(hotel)).isEqualTo(hotel);
	}
	
	@Test
    public void getAllHotels()
    {
	 	   List<Hotel> hotels = new ArrayList<>();
		   hotels.add(new Hotel(2,"Hyder","HYD",20,5,5));
		   hotels.add(new Hotel(3,"hhh","HYDl",20,15,15));
        when(hotelRepository.findAll()).thenReturn(hotels);
        assertThat(hotelServiceImpl.getAllHotels()).isEqualTo(hotels);
    }
	
}

//       Hotel hotel1 = new Hotel();
//		hotel1.setHotelId(1);
//		hotel1.setCity("Delhi");
//		hotel1.setCityCode(60);
//		hotel1.setHotelname("Dharma");
//		hotel1.setTotalrooms(5);
//		hotel1.setRoomsavailable(5);
//		
//	    Hotel hotel2 = new Hotel();
//		 hotel2.setHotelId(2);
//		 hotel2.setCity("Dehradun");
//			hotel2.setCityCode(50);
//			hotel2.setHotelname("Dhargy");
//			hotel2.setTotalrooms(6);
//			hotel2.setRoomsavailable(6);