package net.java.hotelbooking.springboot.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.java.hotelbooking.springboot.model.Booking;
//import net.java.hotelbooking.springboot.model.Hotel;


@Repository
@Transactional
public interface BookingRepository extends JpaRepository<Booking,Long> {
//
    void deleteAllByBookingId(long bookingId);

	List<Booking> findAllByHotelIdAndCancelAndCheckin(long hotelId, int cancel, String checkin);

	List<Booking> findAllByBookingIdAndCancel(long bookingId,int cancel);
	
	List<Booking> findAllByBookingId(long bookingId);

}
