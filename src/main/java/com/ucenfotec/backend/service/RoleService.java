package com.ucenfotec.backend.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucenfotec.backend.model.Role;
import com.ucenfotec.backend.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Transactional
	public List<Role> getAllRoles() {

		List<Role> roleList = roleRepository.findAll();

		return roleList;

	}

	@Transactional
	public Optional<Role> getRoleById(int id) {

		Optional<Role> role = roleRepository.findById(id);
		return role;

	}

	@Transactional
	public Role createRole(Role role) {

		return roleRepository.save(role);

	}

}