package net.java.hotelbooking.springboot.controller;

import java.text.ParseException;
//import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
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


//import lombok.extern.slf4j.Slf4j;
import net.java.hotelbooking.springboot.exception.NoHotelFound;
//import net.java.hotelbooking.springboot.model.Customer;
import net.java.hotelbooking.springboot.model.Hotel;
import net.java.hotelbooking.springboot.service.HotelService;



//@Slf4j
@RestController
@RequestMapping("/api/hotels")
public class HotelController {
	
    private HotelService hotelService;
	public HotelController(HotelService hotelService) {
		super();
		this.hotelService = hotelService;}
    Logger logger = LoggerFactory.getLogger(HotelController.class);
    
    
	@PostMapping()
	public ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotel){
		ResponseEntity<Hotel> h = null;
		h = new ResponseEntity<Hotel>(hotelService.saveHotel(hotel),HttpStatus.CREATED);
		logger.info("New hotel is added in database with hotel Id " + hotel.getHotelId());
		return h;}
	
	
	@GetMapping()
	public List<Hotel> getAllHotels(){
		return hotelService.getAllHotels();	}
	
	
	@GetMapping("{hotelId}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable("hotelId") long hotelId){
		return new ResponseEntity<Hotel>(hotelService.getHotelById(hotelId),HttpStatus.OK);
	}
	

	@PutMapping("{hotelId}")
	public ResponseEntity<Hotel> updateHotel(@PathVariable("hotelId") long hotelId, @RequestBody Hotel hotel){
		return new ResponseEntity<Hotel>(hotelService.updateHotel(hotel, hotelId),HttpStatus.OK);
	}


	@DeleteMapping("{hotelId}")
	public ResponseEntity<String> deleteHotel(@PathVariable("hotelId")long hotelId){
			hotelService.deleteHotel(hotelId);
			return new ResponseEntity<String>("Hotel with Id :"+ hotelId +" " + "Deleted succesfully!.",HttpStatus.OK);}
	
	
    @GetMapping("/city/{cityCode}")
    public List<Hotel> getHotelListById(@PathVariable("cityCode") int cityCode) {
        return hotelService.getHotelListById(cityCode);
    }
    
    
    @GetMapping("/available/")
    public ResponseEntity<List<Hotel>> getHotelList(@RequestParam(name = "hotelId", required = false) long hotelId,
                                                    @RequestParam(name = "checkIn", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkIn,
                                                    @RequestParam(name = "checkOut", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkOut) throws ParseException {
        ResponseEntity<List<Hotel>> res = null;
        logger.info("Get hotels list request received---> HotelId:- " + hotelId + " on " + checkIn);
        if (checkIn == null)
            throw new NoHotelFound("Please Enter check in date");
        if(hotelId == 0)
        	throw new NoHotelFound("please Enter correct HotelId");
        res = new ResponseEntity<List<Hotel>>(hotelService.getHotelList(hotelId, checkIn, checkOut), HttpStatus.OK);
        return res;
    }
    
    
    @ExceptionHandler({NoHotelFound.class})
    public String exceptionHandler_1(Exception e) {
        return "Hotel Not Found, Kindly Check again!..";
    }

    
}

