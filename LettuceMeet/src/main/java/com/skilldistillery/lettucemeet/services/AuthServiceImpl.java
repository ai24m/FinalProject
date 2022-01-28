package com.skilldistillery.lettucemeet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.lettucemeet.entities.Address;
import com.skilldistillery.lettucemeet.entities.User;
import com.skilldistillery.lettucemeet.repositories.AddressRepository;
import com.skilldistillery.lettucemeet.repositories.UserRepository;

@Service 
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AddressRepository addrRepo;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public User register(User user, Address addr) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setEnabled(true);
		user.setRole("standard");
		user.setAddress(addrRepo.saveAndFlush(addr));
		userRepo.saveAndFlush(user);
		return user;
	}
	
	@Override
	public User findUserByName(String username) {
		return userRepo.findByUsername(username);
	}

}
