package net.java.hotelbooking.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name= "Booking")
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bookingId;
	
	@Column(name = "HotelId",nullable = false)
	private long hotelId;
	
	@Column(name = "CheckIn",nullable = false)
	private String checkin;
		
	@Column(name = "CheckOut")
	private String checkout;
	
	@Column(name = "Cancel")
    private int cancel;
	
}
