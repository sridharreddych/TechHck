package com.hcl.myhotel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hcl.myhotel.dao.jpa.HotelRepository;
import com.hcl.myhotel.domain.Hotel;
import com.hcl.myhotel.exception.HotelDetailsNotFoundException;

/**
 * @author Sridhar reddy Hotel Service class
 */
@Service
public class HotelServiceImpl implements HotelService {

	private static final Logger log = LoggerFactory.getLogger(HotelServiceImpl.class);

	@Autowired
	private HotelRepository hotelRepository;

	public HotelServiceImpl() {
	}

	public Hotel createHotel(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	public Hotel getHotel(long id) throws HotelDetailsNotFoundException{
		return hotelRepository.findOne(id);
	}

	public Hotel updateHotel(Hotel hotel) throws HotelDetailsNotFoundException {
		return hotelRepository.save(hotel);
	}

	public void deleteHotel(Long id)  throws HotelDetailsNotFoundException{

		if (!hotelRepository.existsById(id))
			try {
				throw new HotelDetailsNotFoundException("Hotel doesnt exist with the given id");
			} catch (HotelDetailsNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		hotelRepository.delete(id);

	}

	public Page<Hotel> getAllHotels(Integer page, Integer size) {
		Page pageOfHotels = hotelRepository.findAll(new PageRequest(page, size));
		return pageOfHotels;
	}
}
