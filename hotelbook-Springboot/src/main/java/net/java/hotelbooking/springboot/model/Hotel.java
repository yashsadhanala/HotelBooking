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
@Table(name= "Hotels")
public class Hotel {
	
	public Hotel(int i, String string, String string2, int j, int k, int l) {}
	public Hotel() {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long hotelId;
	
	@Column(name = "HotelName",nullable = false)
	private String hotelname;
	
	@Column(name = "City",nullable = false)
	private String city;
	
	@Column(name = "CityPin",nullable = false)
	private int cityCode;
	
	@Column(name = "TotalRooms")
	private int totalrooms;
	
	@Column(name = "RoomsAvailable")
	private int roomsavailable = totalrooms;

}


