package com.ucenfotec.backend.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ucenfotec.backend.model.Address;
import com.ucenfotec.backend.model.Hotel;
import com.ucenfotec.backend.model.User;
import com.ucenfotec.backend.service.AddressService;
import com.ucenfotec.backend.service.HotelService;
import com.ucenfotec.backend.service.UserService;
import com.ucenfotec.backend.virtualobjects.HotelVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("api/hotel")
@Api(tags = "Hotel")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	@Autowired
	private UserService userService;
	@Autowired
	private AddressService addressService;

	@GetMapping
	@ApiOperation(value = " Obtiene todos los hoteles")
	public List<Hotel> getAllHotels() {
		return hotelService.getAllActiveHotels();
	}

	@GetMapping("/{id}")
	@ApiOperation(value = " Obtiene un hotel por si Id ")
	public Hotel getHotelById(@PathVariable int id) {
		return hotelService.getHotelById(id).get();
	}

	@GetMapping("/{province}")
	@ApiOperation(value = " Obtiene todos los hoteles por provincia ")
	public List<Hotel> getHotelByProvince(@PathVariable String province) {
		return hotelService.getHotelsByProvince(province);
	}

	@PostMapping("/create/{id}")
	@ApiOperation(value = " Crea un hotel (Administrador) (El Id se refiere al Id del Administrador) ")
	public ResponseEntity<Hotel> createHotel(@RequestBody HotelVO hotelVO, @RequestParam int id) {

		Optional<User> admin = userService.getUserById(id);

		if (admin.isPresent()) {

			Hotel hotel = new Hotel();
			Address address = new Address();
			address.setProvince(hotelVO.getProvince());
			address.setCanton(hotelVO.getCanton());
			address.setDistrict(hotelVO.getDistrict());

			address = addressService.createAddress(address);

			hotel.setName(hotelVO.getName());
			hotel.setDescription(hotelVO.getDescription());
			hotel.setScore(0);
			hotel.setPhoneNumber(hotelVO.getPhoneNumber());
			hotel.setEmail(hotelVO.getEmail());
			hotel.setImageURL(hotelVO.getImageURL());
			hotel.setActive(true);
			hotel.setUser(admin.get());
			hotel.setAddress(address);

			hotel = hotelService.createHotel(hotel);

			return new ResponseEntity<Hotel>(hotel, HttpStatus.CREATED);

		}
		return new ResponseEntity<Hotel>(HttpStatus.BAD_REQUEST);

	}

	@PutMapping("/update/{id}")
	@ApiOperation(value = " Actualiza un hotel (Administrador) ")
	public ResponseEntity<Hotel> updateHotel(@RequestParam int id) {

		Optional<Hotel> hotel = hotelService.getHotelById(id);

		if (hotel.isPresent()) {

			return new ResponseEntity<Hotel>(hotel.get(), HttpStatus.OK);

		}

		return new ResponseEntity<Hotel>(hotel.get(), HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = " Elimina un hotel (Administrador)")
	public void deleteHotel(@RequestParam int id) {
		hotelService.deleteHotel(id);
	}
}
