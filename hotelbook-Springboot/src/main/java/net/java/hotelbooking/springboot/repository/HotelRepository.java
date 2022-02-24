package net.java.hotelbooking.springboot.repository;

import java.util.List;
//import java.util.Optional;

//import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import net.java.hotelbooking.springboot.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long>{

	

	List<Hotel> findAllByHotelId(long hotelId);

	List<Hotel> findByCityCode(int cityCode);

   // public List<Hotel> findAllByCity(String city);

}
