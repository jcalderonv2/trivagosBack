package com.ucenfotec.backend.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucenfotec.backend.model.Address;
import com.ucenfotec.backend.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Transactional
	public Optional<Address> getAddressById(int id) {

		Optional<Address> address = addressRepository.findById(id);
		return address;

	}

	@Transactional
	public Address createAddress(Address address) {

		return addressRepository.save(address);

	}

	@Transactional
	public Address updateAddress(Address address) {

		return addressRepository.save(address);

	}

}
