package com.ucenfotec.backend.service;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucenfotec.backend.model.Hotel;
import com.ucenfotec.backend.repository.HotelRepository;

@Service
public class HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	@Transactional
	public List<Hotel> getAllActiveHotels() {

		List<Hotel> hotelList = hotelRepository.getActiveHotels();

		return hotelList;

	}

	@Transactional
	public Optional<Hotel> getHotelById(int id) {

		Optional<Hotel> hotel = hotelRepository.findById(id);

		return hotel;

	}

	@Transactional
	public Hotel createHotel(Hotel hotel) {

		return hotelRepository.save(hotel);

	}

	@Transactional
	public Hotel updateHotel(Hotel hotel) {

		return hotelRepository.save(hotel);

	}

	public void deleteHotel(int id) {

		Optional<Hotel> hotelFromdb = hotelRepository.findById(id);
		Hotel hotel = hotelFromdb.get();
		if (hotel.isActive() == true) {
			hotel.setActive(false);
		} else {
			hotel.setActive(true);
		}

		hotelRepository.save(hotel);
	}

	@Transactional
	public List<Hotel> getHotelsByProvince(String province) {

		List<Hotel> hotelList = hotelRepository.findByProvince(province);

		return hotelList;

	}

	@Transactional
	public List<Hotel> searchHotel(String searchString) {

		return hotelRepository.searchHotels(searchString);

	}


}
