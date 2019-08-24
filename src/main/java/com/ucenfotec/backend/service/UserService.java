package com.ucenfotec.backend.service;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucenfotec.backend.model.User;
import com.ucenfotec.backend.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public Set<User> getAllActiveUsers() {

		Set<User> userList = userRepository.getAllActiveUsers();

		return userList;

	}

	@Transactional
	public Optional<User> getUserById(int id) {

		Optional<User> user = userRepository.findById(id);
		return user;

	}

	@Transactional
	public User createUser(User user) {

		return userRepository.save(user);

	}

	@Transactional
	public User updateUser(User user) {

		return userRepository.save(user);

	}

	public void deleteUser(int id) {

		Optional<User> userFromdb = userRepository.findById(id);
		User user = userFromdb.get();
		if (user.isActive() == true) {
			user.setActive(false);
		} else {
			user.setActive(true);
		}

		userRepository.save(user);
	}

	@Transactional
	public Set<User> searchHotel(String searchString) {

		return userRepository.searchUsers(searchString);

	}

}