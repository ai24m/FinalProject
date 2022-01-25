package com.skilldistillery.lettucemeet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.lettucemeet.entities.User;
import com.skilldistillery.lettucemeet.repositories.UserRepository;

@Service 
public class AuthServiceImpl implements AuthService {

	@Autowired 
	private UserRepository userRepo; 
	
	@Override
	public User register(User user) {
		
		return null;
	}

}
