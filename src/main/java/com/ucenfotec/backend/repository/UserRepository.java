package com.ucenfotec.backend.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ucenfotec.backend.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "SELECT u FROM User u WHERE u.active= 1")
	public Set<User> getAllActiveUsers();

	@Query(value = "SELECT u FROM User u WHERE  u.active= 1 and (u.firstName LIKE ?1||'%' OR u.lastName LIKE ?1||'%' OR u.email LIKE ?1||'%' ) ")
	public Set<User> searchUsers(String searchString);

}
