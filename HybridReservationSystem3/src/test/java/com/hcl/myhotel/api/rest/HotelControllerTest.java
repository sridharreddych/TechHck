package com.hcl.myhotel.api.rest;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.hcl.myhotel.api.rest.HotelController;
import com.hcl.myhotel.domain.Hotel;
import com.hcl.myhotel.exception.HotelDetailsNotFoundException;
import com.hcl.myhotel.exception.RecordNotFoundException;
import com.hcl.myhotel.service.HotelServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class HotelControllerTest {

	private org.springframework.test.web.servlet.MockMvc mockMvc;
	
	@InjectMocks
	private HotelController hotelController;

	@Mock
	private HotelServiceImpl hotelServiceImpl;

	private static final Long HOTEL_ID = 1L;

	Hotel hotel;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(hotelController).build();
		hotel = new Hotel();
		hotel.setCity("hyd");
		hotel.setDescription("rich city");
		hotel.setId(1);
		hotel.setName("sree");
		hotel.setRating(7);
	}

	@Test
	public void testCreateHotel() throws Exception {

		Mockito.when(hotelServiceImpl.createHotel(hotel)).thenReturn(hotel);
		ResponseEntity<Hotel> hotelEntity = hotelController.createHotel(hotel);
		assertNotNull(hotelEntity);
	}

	@Test(expected=RecordNotFoundException.class)
	public void testSaveLoanDetailsException() throws Exception{		
		Mockito.when(hotelServiceImpl.getHotel(HOTEL_ID)).thenThrow(new RecordNotFoundException());
		hotelController.getHotel(HOTEL_ID);		
	}

	@Test
	public void testUpdateHotelDetails() {
		try {
			hotelController.updateHotel(HOTEL_ID, hotel);
		} catch (HotelDetailsNotFoundException e) {

		}
	}
	
	@Test
	public void deleteOneHotelByPassingHotelId() throws Exception {
		Long id = 1L;
		
		Mockito.doNothing().when(hotelServiceImpl).deleteHotel(id);
		hotelController.deleteHotel(id);
		Mockito.verify(hotelServiceImpl, Mockito.times(1)).deleteHotel(id);
	}
	
	
	}
