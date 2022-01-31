package com.skilldistillery.lettucemeet.services;

import java.util.List;

import com.skilldistillery.lettucemeet.entities.Address;
import com.skilldistillery.lettucemeet.entities.User;

public interface UserService {

	// get User
	public List<User> getAllUser();

	public User findByUserName(String name);
	
	// get By ID
	public User getUserById(int userId);

	// CRUD
	public User createdUser(User user, Address address);

	public User updateUser(int id, User user);

	public boolean deleteUser(int userId);

	public User getUserByUsername(String username);

}
