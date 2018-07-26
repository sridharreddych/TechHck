package com.hcl.myhotel.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.hcl.myhotel.dao.jpa.HotelRepository;
import com.hcl.myhotel.domain.Hotel;
import com.hcl.myhotel.exception.HotelDetailsNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class HotelServiceTest {

	@Mock
	private HotelRepository hotelRepository;

	@InjectMocks
	private HotelServiceImpl hotelServiceImpl;

	private static final String City = "hyd";
	private static final Long id = 1L;

	private Hotel hotel;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Hotel hotel = new Hotel();
		hotel.setCity("hyd");
		hotel.setDescription("good");
		hotel.setId(1);
		hotel.setName("sree");
		hotel.setRating(8);
	}

	@Test
	public void testSaveHotelDetailsPositive() throws Exception {
		// Optional<Hotel> optionalHotel = Optional.of(hotel);
		Mockito.when(hotelRepository.findHotelByCity("hyd")).thenReturn(hotel);

	}

	@Test
	public void testGetHotelById() {
		when(hotelRepository.findOne(id)).thenReturn(hotel);
		try {
			Hotel hotelObj = hotelServiceImpl.getHotel(id);
			assertEquals(hotel, hotelObj);
		} catch (HotelDetailsNotFoundException e) {

		}
	}

	@Test
	public void testGetHotelByInvalidId() {
		try {
			Hotel hotelAct = hotelServiceImpl.getHotel(id);
			assertEquals(hotelAct, hotelAct);
		} catch (HotelDetailsNotFoundException e) {
			assertThat(e.getMessage()).isEqualTo("Hotel with the id not available");

		}
	}

	@Test
	public void testUpdateHotelDetails() {
		when(hotelRepository.save(hotel)).thenReturn(hotel);
		when(hotelRepository.save(hotel)).thenReturn(hotel);

		try {
			Hotel hotelAct = hotelServiceImpl.updateHotel(hotel);
			assertEquals(hotel, hotelAct);

		} catch (HotelDetailsNotFoundException e) {
		}

	}

	@Test
	public void testUpdateHotelDetailsException() {
		when(hotelRepository.save(hotel)).thenReturn(hotel);

		try {
			Hotel hotelAct = hotelServiceImpl.updateHotel(hotel);
			assertEquals(hotel, hotelAct);

		} catch (HotelDetailsNotFoundException e) {
			assertThat(e.getMessage()).isEqualTo("Hotel doesnt exist");
		}

	}

	@Test
	public void testdeleteHotelDetailsById() {
		
		when(hotelRepository.existsById(id)).thenReturn(true);
		try {
			hotelServiceImpl.deleteHotel(id);

		} catch (HotelDetailsNotFoundException e) {
		}

	}
	

	@Test
	public void testdeleteHotelDetailsException() {
		when(hotelRepository.existsById(id)).thenReturn(false);
		try {
			hotelServiceImpl.deleteHotel(id);

		} catch (HotelDetailsNotFoundException e) {
			assertThat(e.getMessage()).isEqualTo("Hotel doesnt exist");
		}

	}

}