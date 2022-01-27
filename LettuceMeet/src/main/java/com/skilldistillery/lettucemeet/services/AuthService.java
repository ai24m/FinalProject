package com.skilldistillery.lettucemeet.services;

import com.skilldistillery.lettucemeet.entities.Address;
import com.skilldistillery.lettucemeet.entities.User;

public interface AuthService {

	User register(User user, Address address); //new user 

	User findUserByName(String username);
	
}
