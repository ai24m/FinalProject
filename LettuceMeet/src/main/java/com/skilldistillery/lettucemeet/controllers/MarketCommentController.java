package com.skilldistillery.lettucemeet.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.skilldistillery.lettucemeet.entities.*;
import com.skilldistillery.lettucemeet.services.MarketCommentService;
import com.skilldistillery.lettucemeet.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300"})
public class MarketCommentController {
	
	@Autowired 
	private MarketCommentService mcSvc; 
	
	@Autowired 
	private UserService userSvc; 
	
	@GetMapping("marketcomments")
	public List<MarketComment> index(HttpServletRequest req, HttpServletResponse res){
		List<MarketComment> allMarketComments = mcSvc.index(); 
		if (allMarketComments == null) {
			res.setStatus(404);
		} return allMarketComments; 
	}
	
	@GetMapping("marketcomments/{mcId}")
	public MarketComment show(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer mcId) {
		MarketComment marketComment = mcSvc.show(mcId);
		if (marketComment == null) {
			res.setStatus(404);
		} else {
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(marketComment.getId());
			res.setHeader("location", url.toString());
		} return marketComment; 
	}
	
	@PostMapping("marketcomments") //must create address with market 
	public MarketComment create(HttpServletRequest req, HttpServletResponse res, Principal principal, @RequestBody MarketComment marketComment,
			@RequestBody Address address) {
		try {
			User user = userSvc.findByUserName(principal.getName()); 
			marketComment = mcSvc.create(marketComment, user); 
			if (marketComment == null) {
				res.setStatus(404);
				return marketComment; 
			}
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(marketComment.getId());
			res.setHeader("location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		} return marketComment; 
	}
	
	@PutMapping("marketcomments/{mcId}")
	public Market update(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable Integer mcId, @RequestBody MarketComment marketComment) {
		try {
			User user = userSvc.findByUserName(principal.getName()); 
			marketComment = mcSvc.update(user, mcId, marketComment);
			if (marketComment == null) {
				res.setStatus(404); // 404 request body does not exisy
				return null;
			}
		} catch (Exception e) {
			res.setStatus(400); // 400 request body is bad data
			e.printStackTrace();
			marketComment = null;
		}
		return marketComment;
	}

}
