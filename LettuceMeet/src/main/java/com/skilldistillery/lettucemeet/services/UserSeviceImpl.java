package com.skilldistillery.lettucemeet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.lettucemeet.entities.Address;
import com.skilldistillery.lettucemeet.entities.User;
import com.skilldistillery.lettucemeet.repositories.AddressRepository;
import com.skilldistillery.lettucemeet.repositories.UserRepository;

@Service
public class UserSeviceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AddressRepository addressRepo;

	@Override
	public List<User> getAllUser() {
		return userRepo.findAll();
	}

	@Override
	public User findByUserName(String name) {
		return userRepo.findByUsername(name);
	}

	@Override
	public User getUserById(int userId) {
		Optional<User> op = userRepo.findById(userId);
		User user = null;
		if (op.isPresent()) {
			user = op.get();
		}
		return user;
	}

	@Override
	public User createdUser(User user, Address address) {
		addressRepo.saveAndFlush(address);
		user.setAddress(address);
		userRepo.saveAndFlush(user);
		return user;
	}

	@Override
	public User updateUser(int id, User user) {
		Optional<User> op = userRepo.findById(id);
		User managed = null;
		if (op.isPresent()) {
			managed = op.get();
			managed.setBusinessName(user.getBusinessName());
			managed.setEmail(user.getEmail());
			managed.setFirstName(user.getFirstName());
			managed.setImageUrl(user.getImageUrl());
			managed.setLastName(user.getLastName());
			managed.setUsername(user.getUsername());
			return userRepo.saveAndFlush(managed);
		}
		return managed;
	}

	@Override
	public boolean deleteUser(int userId) {
		boolean deleted = false;
		Optional<User> op = userRepo.findById(userId);
		if (op.isPresent()) {
			User user = op.get();
			userRepo.delete(user);
			deleted = true;
		}
		return deleted;
	}

}
