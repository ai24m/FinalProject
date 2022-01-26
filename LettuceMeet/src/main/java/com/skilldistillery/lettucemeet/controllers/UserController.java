package com.skilldistillery.lettucemeet.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.lettucemeet.entities.Address;
import com.skilldistillery.lettucemeet.entities.User;
import com.skilldistillery.lettucemeet.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*","http://localhost:4300"})
public class UserController {
	
	@Autowired
	private UserService userSev;
	
	@Autowired
	
	

	@PostMapping("users")
	public User create(HttpServletRequest req, 
			HttpServletResponse res, 
			User user,
			Address address) {
		
		try {
			user = userSev.createdUser(user, address);
			if(user ==null) {
				res.setStatus(404);
				return user;
			}
			else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(user.getId());
				res.setHeader("lOcation", url.toString());
			}
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			user = null;
		}
		return user;
	}
}
