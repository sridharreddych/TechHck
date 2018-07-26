package com.hcl.myhotel.service;

import org.springframework.data.domain.Page;

import com.hcl.myhotel.domain.Hotel;
import com.hcl.myhotel.exception.HotelDetailsNotFoundException;
import com.hcl.myhotel.exception.HotelIDNotFoundException;


/**
 * @author Sridhar reddy
 *Hotel Service Interface
 */
public interface HotelService {

	public Hotel createHotel(Hotel hotel);
	public Hotel getHotel(long id) throws HotelDetailsNotFoundException;
	public Hotel updateHotel(Hotel hotel) throws HotelDetailsNotFoundException;
	public void deleteHotel(Long id)  throws HotelDetailsNotFoundException; 
	public Page<Hotel> getAllHotels(Integer page, Integer size);
}
