package com.skilldistillery.lettucemeet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.lettucemeet.entities.Address;
import com.skilldistillery.lettucemeet.entities.User;
import com.skilldistillery.lettucemeet.repositories.UserRepository;

@Service
public class UserSeviceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AddressRepository addressRepo;

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		
		return userRepo.findAll();
	}

	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		Optional<User> op = userRepo.findById(userId);
		User user = null;
		if(op.isPresent()) {
			user = op.get();
		}
		return user;
	}

	@Override
	public User createdUser( User user, Address address) {
		// TODO Auto-generated method stub
		addressRepo.saveAndFlush(address);
		user.setAddress(address);
		userRepo.saveAndFlush(user);
		return user;
	}

	@Override
	public User updateUser(int id, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUser(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

}
