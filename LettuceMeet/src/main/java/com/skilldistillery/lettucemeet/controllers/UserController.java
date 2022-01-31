package com.skilldistillery.lettucemeet.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.lettucemeet.entities.Address;
import com.skilldistillery.lettucemeet.entities.User;
import com.skilldistillery.lettucemeet.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300" })
public class UserController {

	@Autowired
	private UserService userSev;

	@GetMapping("users/username")
	public User getUserByUsername(Principal principal) {
		User toReturn = userSev.getUserByUsername(principal.getName());
		return toReturn;
	}
	

	@PutMapping("users/{userId}")
	public User updateUser(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId,
			@RequestBody User user, Principal principal) {
		try {
			User user1 = userSev.getUserById(userId);
			if(principal.getName().equals(user1.getUsername())) {
			user = userSev.updateUser(userId, user);
				if(user == null) {
				res.setStatus(404);
				}
				return user;
			}
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			user = null;
		}
		return user;
	}

	@DeleteMapping("users/{userId}")
	public void deleteUser(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId,
			Principal principal) {
		try {
			User user = userSev.getUserById(userId);
			if (principal.getName().equals(user.getUsername())) {
				if (userSev.deleteUser(userId)) {
					res.setStatus(HttpStatus.NO_CONTENT.value());
				} else {
					res.setStatus(404);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}

}
