package com.ucenfotec.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ucenfotec.backend.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

	@Query(value = "SELECT h FROM Hotel h WHERE  h.active= 1 and (h.name LIKE ?1||'%') ")
	public List<Hotel> searchHotels(String searchString);

	@Query(value = "SELECT h FROM Hotel h WHERE h.active= 1")
	public List<Hotel> getActiveHotels();

	@Query(value = "SELECT h FROM Hotel h JOIN h.address WHERE h.active= 1 and (h.address.province LIKE ?1||'%') ")
	public List<Hotel> findByProvince(String province);

}

