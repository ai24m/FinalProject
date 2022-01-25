package com.skilldistillery.lettucemeet.services;

import com.skilldistillery.lettucemeet.entities.User;

public interface AuthService {

	User register(User user); //new user 

	User findUserByName(String username);
	
}
