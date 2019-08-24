package com.ucenfotec.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ucenfotec.backend.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
